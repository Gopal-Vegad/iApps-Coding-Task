package com.iapps.practical.data.remote.feed

import com.iapps.practical.data.remote.ApiService
import com.iapps.practical.model.FeedModel
import com.iapps.practical.utils.Resource
import com.iapps.practical.utils.ResourceHelper
import com.iapps.practical.utils.exts.toFormatDatePublished
import com.iapps.practical.utils.exts.toFormatDateTaken
import javax.inject.Inject

class FeedRemoteServiceImpl @Inject constructor(
    helper: ResourceHelper,
    private val api: ApiService
):BaseApiService(helper), IFeedRemoteService {
    override suspend fun getFeeds(): Resource<List<FeedModel>> {
        val result = safeApiCall { api.getFeeds() }
        if(result is Resource.Success) {
            return Resource.Success(
                data = result.data?.items?.mapNotNull { item ->
                    item?.let {
                        FeedModel(
                            id = 0,
                            title = it.title.toString(),
                            it.link.toString(),
                            it.media?.m.toString(),
                            it.dateTaken?.toFormatDateTaken(),
                            it.description.toString(),
                            it.published?.toFormatDatePublished(),
                            it.author.toString(),
                            it.authorId.toString(),
                            it.tags.toString()
                        )
                    }
                } ?: emptyList<FeedModel>()
            )
        }else {
            return Resource.Error(message = result.message)
        }
    }


}