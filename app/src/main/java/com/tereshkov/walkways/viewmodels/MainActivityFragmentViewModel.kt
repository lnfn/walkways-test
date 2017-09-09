package com.tereshkov.walkways.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tereshkov.walkways.data.BusSheduleEvents
import com.tereshkov.walkways.data.WalkwaysResponse
import com.tereshkov.walkways.data.repositories.IEventsTimelineRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivityFragmentViewModel @Inject constructor(
        private val eventsTimelineRepository: IEventsTimelineRepository
) : ViewModel() {
    val events = MutableLiveData<List<BusSheduleEvents>>()

    fun loadEvents() {
        eventsTimelineRepository.getTimelineEvents().enqueue(object : Callback<WalkwaysResponse> {
            override fun onFailure(call: Call<WalkwaysResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<WalkwaysResponse>?, response: Response<WalkwaysResponse>?) {
                response?.body()?.bus_shedule_events?.let { events.value = it }
            }
        })
    }
}