package com.tereshkov.walkways.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tereshkov.walkways.EventsAdapter
import com.tereshkov.walkways.R
import com.tereshkov.walkways.data.BusSheduleEvents
import com.tereshkov.walkways.di.Injectable
import com.tereshkov.walkways.viewmodels.MainActivityFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainActivityFragment : BaseFragment(), Injectable {
    override val layoutId: Int = R.layout.fragment_main
    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var vm: MainActivityFragmentViewModel
    private val mAdapter: EventsAdapter = EventsAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this, factory).get(MainActivityFragmentViewModel::class.java)
        vm.events.observe(this, Observer { events ->
            mAdapter.refreshData(events as List<BusSheduleEvents>)
        })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subMenuButton.setOnClickListener { vm.goToFragmentSubMenu() }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        vm.loadEvents()
    }
}