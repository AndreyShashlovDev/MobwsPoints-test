package com.sprinter.mobws.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes resId: Int): View {
    val inflater = LayoutInflater.from(this.context)
    return inflater.inflate(resId, this, false)
}
