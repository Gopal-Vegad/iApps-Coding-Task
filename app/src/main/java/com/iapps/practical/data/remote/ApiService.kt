package com.iapps.practical.data.remote

import com.iapps.practical.data.remote.feed.response.FeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/services/feeds/photos_public.gne")
    suspend fun getFeeds(
        @Query("format") format: String = "json",
        @Query("tags") tag: String = "cat",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
    ): Response<FeedResponse?>
}