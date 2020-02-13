package com.sprinter.mobws.di

import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragmentDirections
import com.sprinter.mobws.router.Router
import com.sprinter.mobws.router.RouterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RouterModule {

    @Singleton
    @Provides
    fun provideSplashRouter(): Router<DashboardFragmentDirections> = RouterImpl()
}
