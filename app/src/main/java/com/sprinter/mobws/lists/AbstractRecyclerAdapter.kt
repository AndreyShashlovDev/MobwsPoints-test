package com.sprinter.mobws.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface PagedInteraction {
    val prefetchDistance: Int

    fun onFetchNext(): Boolean
}

abstract class AbstractRecyclerAdapter<D, T : AbstractHolder<D>>(
    private val diffCallback: DiffUtil.ItemCallback<D>,
    private var pagedInteraction: PagedInteraction? = null
) : RecyclerView.Adapter<T>() {

    private var differ: Differ<D> =
        Differ(this, diffCallback, emptyList(), emptyList())

    private var loadingState: PageableLoadedState = PageableLoadedState.Loaded

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val inflater = LayoutInflater.from(parent.context)

        return onInitViewHolder(parent, inflater, viewType)
    }

    protected abstract fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): T

    open override fun onBindViewHolder(holder: T, position: Int) {
        holder.setData(differ.getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        if (pagedInteraction != null && loadingState == PageableLoadedState.Loaded &&
            differ.size > pagedInteraction!!.prefetchDistance &&
            differ.size - position < pagedInteraction!!.prefetchDistance
        ) {
            loadingState = if (pagedInteraction?.onFetchNext() == true)
                PageableLoadedState.Loading else PageableLoadedState.Loaded
        }

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return differ.size
    }

    fun submitList(list: List<D>) {
        differ = Differ(this, diffCallback, list, differ.newList)
        differ.calculateDiff()
        loadingState = PageableLoadedState.Loaded
    }

    override fun onViewRecycled(holder: T) {
        holder.unbind()

        super.onViewRecycled(holder)
    }
}
