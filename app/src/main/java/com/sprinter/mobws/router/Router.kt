package com.sprinter.mobws.router

import androidx.navigation.NavDirections
import com.sprinter.mobws.utils.SingleLiveEvent

interface Router<T> {

    fun navigate(): SingleLiveEvent<NavDirections>

    fun routeTo(direction: NavDirections)
}
