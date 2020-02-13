package com.sprinter.mobws.di

import com.sprinter.mobws.utils.ExceptionHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExceptionHandlerModule {

    @Singleton
    @Provides
    fun provideExceptionHandler() = ExceptionHandler()
}
