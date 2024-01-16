package com.iapps.practical.domain

import com.iapps.practical.data.local.dao.FeedDao
import com.iapps.practical.data.local.entity.toFeedModel
import com.iapps.practical.data.prefs.SharedPreferenceKey
import com.iapps.practical.data.prefs.SharedPreferencesManager
import com.iapps.practical.data.remote.feed.IFeedRemoteService
import com.iapps.practical.di.IoDispatcher
import com.iapps.practical.model.FeedModel
import com.iapps.practical.model.toFeedEntity
import com.iapps.practical.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val prefs: SharedPreferencesManager,
    private val feedDao: FeedDao,
    private val feedService: IFeedRemoteService
) : IMainRepository {
    override val feedStream: Flow<List<FeedModel>>
        get() = feedDao.getFeedStream().map { list -> list.map { it.toFeedModel() } }

    override suspend fun getFeeds(): Resource<Unit> = withContext(ioDispatcher) {
        val result = feedService.getFeeds()
        if (result is Resource.Success) {
            if (prefs.getString(SharedPreferenceKey.LAST_MODIFIED_DATE, "") != result.data?.first) {
                val localFeeds = feedDao.getFeeds()
                val titleWithId = localFeeds.associate { it.title to it.id }
                val remoteFeeds = result.data?.second ?: emptyList()
                val feeds =
                    remoteFeeds.map { it.copy(id = titleWithId[it.title] ?: 0, description = getFormattedDescription(it.description)).toFeedEntity() }
                feedDao.insertFeed(feeds)
                val titles = remoteFeeds.map { it.title }
                val deletedFeeds = localFeeds.filter { !titles.contains(it.title) }
                feedDao.deleteFeed(deletedFeeds)

                prefs.saveString(SharedPreferenceKey.LAST_MODIFIED_DATE, result.data?.first ?: "")
            }
            Resource.Success(data = null)
        } else {
            Resource.Error(message = result.message, data = null)
        }
    }

    private fun getFormattedDescription(description: String): String {
        val document: Document = Jsoup.parse(description)

        // Remove all img tags
        val imgTags: List<Element> = document.select("img")
        imgTags.forEach { it.remove() }

        return document.body().html()
    }

}