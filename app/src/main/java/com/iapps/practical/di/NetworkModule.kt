package com.iapps.practical.di

import com.iapps.practical.BuildConfig
import com.iapps.practical.data.remote.ApiService
import com.iapps.practical.data.remote.feed.FeedRemoteServiceImpl
import com.iapps.practical.data.remote.feed.IFeedRemoteService
import com.iapps.practical.domain.IMainRepository
import com.iapps.practical.domain.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(2, TimeUnit.MINUTES)
        clientBuilder.connectTimeout(2, TimeUnit.MINUTES)
        clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkServiceModule {
    @Binds
    fun bindFeedService(feedRemoteServiceImpl: FeedRemoteServiceImpl): IFeedRemoteService
}