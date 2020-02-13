package com.sprinter.mobws.di

import com.sprinter.mobws.mvvm.dashboard.di.DashboardFragmentModule
import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragment
import com.sprinter.mobws.mvvm.main.di.MainActivityModule
import com.sprinter.mobws.mvvm.main.ui.MainActivity
import com.sprinter.mobws.mvvm.points.di.PointsFragmentModule
import com.sprinter.mobws.mvvm.points.ui.PointsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [DashboardFragmentModule::class])
    abstract fun bindPointsFragment(): DashboardFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PointsFragmentModule::class])
    abstract fun bindPointsResultFragment(): PointsFragment
}
