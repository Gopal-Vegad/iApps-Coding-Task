package com.iapps.practical.domain

import com.iapps.practical.model.FeedModel
import com.iapps.practical.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    val feedStream: Flow<List<FeedModel>>
    suspend fun getFeeds(): Resource<Unit>
}