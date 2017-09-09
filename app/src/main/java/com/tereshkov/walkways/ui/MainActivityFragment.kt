package com.tereshkov.walkways.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tereshkov.walkways.EventsAdapter
import com.tereshkov.walkways.R
import com.tereshkov.walkways.Screens
import com.tereshkov.walkways.data.BusSheduleEvents
import com.tereshkov.walkways.di.Injectable
import com.tereshkov.walkways.viewmodels.MainActivityFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject


class MainActivityFragment : Fragment(), LifecycleRegistryOwner, Injectable {
    @Inject lateinit var factory: ViewModelProvider.Factory
    @Inject lateinit var router: Router
    private lateinit var vm: MainActivityFragmentViewModel
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val mAdapter: EventsAdapter = EventsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this, factory).get(MainActivityFragmentViewModel::class.java)
        vm.events.observe(this, Observer { events ->
            mAdapter.refreshData(events as List<BusSheduleEvents>)
        })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        rectButton.setOnClickListener { router.navigateTo(Screens.RECTANGLE) }
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

    override fun getLifecycle() = lifecycleRegistry
}