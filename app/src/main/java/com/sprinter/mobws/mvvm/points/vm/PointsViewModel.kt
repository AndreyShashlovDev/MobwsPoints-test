package com.sprinter.mobws.mvvm.points.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprinter.mobws.mvvm.points.ui.PointsFragmentArgs
import com.sprinter.mobws.repositories.models.Point
import com.sprinter.mobws.repositories.points.PointsRepository
import kotlinx.coroutines.launch

class PointsViewModel(private val repository: PointsRepository) : ViewModel() {

    private val originalList = MutableLiveData<List<Point>>(emptyList())

    val hasError = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(true)
    val showInterface = MutableLiveData<Boolean>(false)

    private var countPoints = 0

    fun getLiveList(): LiveData<List<Point>> = originalList

    fun setArgs(arg: PointsFragmentArgs) {
        if (countPoints <= 0) {
            countPoints = arg.countPoints
            this.syncPoints(arg.countPoints)
        }
    }

    fun onRetryClick() {
        this.syncPoints(countPoints)
    }

    private fun syncPoints(points: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            hasError.postValue(false)
            showInterface.postValue(false)

            val result = repository.getPoints(points)

            originalList.postValue(result.data)
            hasError.postValue(result.error !== null)

            isLoading.postValue(false)
            showInterface.postValue(result.error == null)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
