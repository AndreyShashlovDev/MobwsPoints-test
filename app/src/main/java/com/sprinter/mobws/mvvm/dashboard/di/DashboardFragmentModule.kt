package com.sprinter.mobws.mvvm.dashboard.di

import androidx.lifecycle.ViewModelProvider
import com.sprinter.mobws.di.FragmentScope
import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragment
import com.sprinter.mobws.mvvm.dashboard.vm.DashboardViewModel
import com.sprinter.mobws.mvvm.dashboard.vm.DashboardViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DashboardFragmentModule {

    @Provides
    @FragmentScope
    fun provideViewModel(
        fragment: DashboardFragment,
        factory: DashboardViewModelFactory
    ) = ViewModelProvider(fragment, factory).get(DashboardViewModel::class.java)
}
