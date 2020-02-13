package com.sprinter.mobws.mvvm.points.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sprinter.mobws.R
import com.sprinter.mobws.databinding.FmtPointsBinding
import com.sprinter.mobws.lists.points.PointsAdapter
import com.sprinter.mobws.mvvm.points.vm.PointsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fmt_points.*
import javax.inject.Inject


class PointsFragment : DaggerFragment() {

    @Inject
    lateinit var adapter: PointsAdapter
    @Inject
    lateinit var viewModel: PointsViewModel

    private val args: PointsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil
            .inflate<FmtPointsBinding>(inflater, R.layout.fmt_points, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pointsList.layoutManager = LinearLayoutManager(context)
        pointsList.adapter = adapter

        viewModel.getLiveList()
            .observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
                pointsChart.setPoints(it)
            })

        viewModel.setArgs(args)
    }
}
