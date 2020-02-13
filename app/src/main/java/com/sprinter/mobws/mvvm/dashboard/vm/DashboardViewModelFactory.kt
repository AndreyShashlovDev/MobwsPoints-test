package com.sprinter.mobws.mvvm.dashboard.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragmentDirections
import com.sprinter.mobws.router.Router

class DashboardViewModelFactory(
    private val router: Router<DashboardFragmentDirections>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(router) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
