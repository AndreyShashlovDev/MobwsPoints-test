package com.sprinter.mobws.mvvm.main.di

import androidx.lifecycle.ViewModelProvider
import com.sprinter.mobws.di.ActivityScope
import com.sprinter.mobws.mvvm.main.ui.MainActivity
import com.sprinter.mobws.mvvm.main.vm.MainViewModel
import com.sprinter.mobws.mvvm.main.vm.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModel(
        activity: MainActivity,
        factory: MainViewModelFactory
    ) = ViewModelProvider(activity, factory).get(MainViewModel::class.java)
}
