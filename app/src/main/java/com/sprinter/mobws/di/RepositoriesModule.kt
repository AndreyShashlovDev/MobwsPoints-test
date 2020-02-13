package com.sprinter.mobws.di

import com.sprinter.mobws.repositories.points.PointsRepository
import com.sprinter.mobws.repositories.points.PointsRepositoryImpl
import com.sprinter.mobws.repositories.source.network.api.PointsApi
import com.sprinter.mobws.utils.ExceptionHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun providePointsRepository(
        pointsApi: PointsApi,
        exceptionHandler: ExceptionHandler
    ): PointsRepository =
        PointsRepositoryImpl(pointsApi, exceptionHandler)
}
