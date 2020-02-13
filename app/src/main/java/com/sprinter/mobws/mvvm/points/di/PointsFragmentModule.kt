package com.sprinter.mobws.mvvm.points.di

import androidx.lifecycle.ViewModelProvider
import com.sprinter.mobws.di.FragmentScope
import com.sprinter.mobws.lists.points.PointsAdapter
import com.sprinter.mobws.mvvm.points.ui.PointsFragment
import com.sprinter.mobws.mvvm.points.vm.PointsViewModel
import com.sprinter.mobws.mvvm.points.vm.PointsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PointsFragmentModule {

    @Provides
    @FragmentScope
    fun provideProductsRecyclerAdapter() = PointsAdapter()

    @Provides
    @FragmentScope
    fun provideViewModel(
        fragment: PointsFragment,
        factory: PointsViewModelFactory
    ) = ViewModelProvider(fragment, factory).get(PointsViewModel::class.java)
}
