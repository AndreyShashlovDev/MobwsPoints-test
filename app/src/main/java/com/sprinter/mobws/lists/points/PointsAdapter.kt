package com.sprinter.mobws.lists.points

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.sprinter.mobws.R
import com.sprinter.mobws.databinding.LiPointsBinding
import com.sprinter.mobws.lists.AbstractRecyclerAdapter
import com.sprinter.mobws.repositories.models.Point


class PointsAdapter : AbstractRecyclerAdapter<Point, PointHolder>(
    ITEM_COMPARATOR
) {

    override fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): PointHolder {
        val binding: LiPointsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.li_points, parent, false
        )
        return PointHolder(binding)
    }

    companion object {
        val ITEM_COMPARATOR: DiffUtil.ItemCallback<Point> =
            object : DiffUtil.ItemCallback<Point>() {
                override fun areItemsTheSame(
                    oldItem: Point,
                    newItem: Point
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: Point,
                    newItem: Point
                ): Boolean = oldItem == newItem
            }
    }
}
