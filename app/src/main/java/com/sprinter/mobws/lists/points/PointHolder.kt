package com.sprinter.mobws.lists.points

import android.view.View
import com.sprinter.mobws.databinding.LiPointsBinding
import com.sprinter.mobws.lists.AbstractHolder
import com.sprinter.mobws.repositories.models.Point

class PointHolder(private val binding: LiPointsBinding) : AbstractHolder<Point>(binding.root) {
    override fun bind(model: Point) {
        super.bind(model)
        binding.model = model
    }
}