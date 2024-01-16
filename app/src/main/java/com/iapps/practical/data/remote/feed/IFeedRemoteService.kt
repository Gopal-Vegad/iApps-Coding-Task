package com.iapps.practical.data.remote.feed

import com.iapps.practical.model.FeedModel
import com.iapps.practical.utils.Resource

interface IFeedRemoteService {
    suspend fun getFeeds():  Resource<Pair<String, List<FeedModel>>>
}