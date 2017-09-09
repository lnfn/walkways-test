package com.tereshkov.walkways.data.services

import com.tereshkov.walkways.data.WalkwaysResponse
import retrofit2.Call
import retrofit2.http.GET


interface WalkwaysService {
    @GET("api/v1/timelineEvents")
    fun getTimelineEvents(): Call<WalkwaysResponse>
}