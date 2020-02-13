package com.sprinter.mobws.di

import com.sprinter.mobws.repositories.source.network.NetworkApiFactory
import com.sprinter.mobws.repositories.source.network.api.PointsApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkApiModule {

    @Singleton
    @Provides
    fun providePointApi(networkApiFactory: NetworkApiFactory) =
        networkApiFactory.create(PointsApi::class.java)
}
