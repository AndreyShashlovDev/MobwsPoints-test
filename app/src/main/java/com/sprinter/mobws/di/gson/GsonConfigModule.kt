package com.sprinter.mobws.di.gson

import com.google.gson.GsonBuilder
import com.sprinter.mobws.repositories.models.UglyResponse
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonConfigModule {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .registerTypeAdapter(UglyResponse::class.java, UglyResponseDeserializer())
        .create()
}
