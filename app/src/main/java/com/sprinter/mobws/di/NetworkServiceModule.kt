package com.sprinter.mobws.di

import com.google.gson.Gson
import com.sprinter.mobws.BuildConfig
import com.sprinter.mobws.repositories.source.network.NetworkApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkServiceModule {

    @Singleton
    @Provides
    fun provideNetworkService(gson: Gson) = NetworkApiFactory
        .Builder(BuildConfig.API_END_POINT, gson)
        .isDebug(BuildConfig.DEBUG)
        .connectionTimeOutSeconds(BuildConfig.CONNECTION_TIME_OUT)
        .build()
}
