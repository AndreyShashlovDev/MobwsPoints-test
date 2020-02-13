package com.sprinter.mobws.lists

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractHolder<out T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    open fun bind(model: @UnsafeVariance T) {}

    open fun unbind() {}

    fun setData(model: @UnsafeVariance T) {
        bind(model)
    }
}
