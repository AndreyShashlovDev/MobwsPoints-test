package com.sprinter.mobws.mvvm.dashboard.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.sprinter.mobws.mvvm.dashboard.ui.DashboardFragmentDirections
import com.sprinter.mobws.router.Router

class DashboardViewModel(private val router: Router<DashboardFragmentDirections>) : ViewModel() {

    val navigator: LiveData<NavDirections>
        get() = router.navigate()

    val pointsCount = MutableLiveData(10)

    val runButtonIsEnabled = Transformations.map(pointsCount) {
        it in MIN_POINTS_COUNT..MAX_POINTS_COUNT
    }

    fun onRunClick() {
        router.routeTo(
            DashboardFragmentDirections
                .actionDashboardFragmentToPointsFragment(pointsCount.value!!)
        )
    }

    companion object {
        const val MAX_POINTS_COUNT = 100
        const val MIN_POINTS_COUNT = 1
    }
}
