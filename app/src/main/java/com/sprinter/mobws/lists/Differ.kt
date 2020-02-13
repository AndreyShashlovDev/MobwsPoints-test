package com.sprinter.mobws.lists

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class Differ<T>(
    private val adapter: RecyclerView.Adapter<*>,
    private val diffCallback: DiffUtil.ItemCallback<T>,
    val newList: List<T>,
    val oldList: List<T>
) : DiffUtil.Callback() {

    val size = newList.size

    fun calculateDiff() {
        val diffResult = DiffUtil.calculateDiff(this)
        diffResult.dispatchUpdatesTo(adapter)
    }

    fun getItem(index: Int): T = newList[index]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = diffCallback.areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = diffCallback.areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])

    override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Any? = diffCallback.getChangePayload(oldList[oldItemPosition], newList[newItemPosition])
}
