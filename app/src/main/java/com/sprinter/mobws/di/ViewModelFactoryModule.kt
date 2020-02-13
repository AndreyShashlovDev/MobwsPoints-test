package com.sprinter.mobws.di

import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragmentDirections
import com.sprinter.mobws.mvvm.dashboard.vm.DashboardViewModelFactory
import com.sprinter.mobws.mvvm.main.vm.MainViewModelFactory
import com.sprinter.mobws.mvvm.points.vm.PointsViewModelFactory
import com.sprinter.mobws.repositories.points.PointsRepository
import com.sprinter.mobws.router.Router
import com.sprinter.mobws.utils.ExceptionHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideMainViewModelFactory(exceptionHandler: ExceptionHandler) =
        MainViewModelFactory(exceptionHandler)

    @Provides
    @Singleton
    fun provideDashboardViewModelFactory(router: Router<DashboardFragmentDirections>) =
        DashboardViewModelFactory(router)

    @Provides
    @Singleton
    fun providePointsViewModelFactory(repository: PointsRepository) =
        PointsViewModelFactory(repository)
}
