package com.sprinter.mobws.router

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sprinter.mobws.utils.SingleLiveEvent

class RouterImpl<T> : Router<T> {

    private val navigation = SingleLiveEvent<NavDirections>()

    override fun navigate(): SingleLiveEvent<NavDirections> = navigation

    override fun routeTo(direction: NavDirections) {
        navigation.postValue(direction)
    }
}
