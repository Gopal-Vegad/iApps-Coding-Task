package com.iapps.practical.di

import com.iapps.practical.domain.IMainRepository
import com.iapps.practical.domain.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): IMainRepository

}