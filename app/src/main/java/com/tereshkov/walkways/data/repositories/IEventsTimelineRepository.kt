package com.tereshkov.walkways.data.repositories

import com.tereshkov.walkways.data.WalkwaysResponse
import com.tereshkov.walkways.data.services.WalkwaysService
import retrofit2.Call
import javax.inject.Inject


interface IEventsTimelineRepository {
    fun getTimelineEvents(): Call<WalkwaysResponse>
}

class EventsTimelineRepository @Inject constructor(
        val walkwaysService: WalkwaysService
) : IEventsTimelineRepository {

    override fun getTimelineEvents(): Call<WalkwaysResponse> = walkwaysService.getTimelineEvents()
}