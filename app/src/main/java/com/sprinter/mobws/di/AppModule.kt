package com.sprinter.mobws.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppContextViaApp(app: DaggerApplication): Context = app.applicationContext
}
