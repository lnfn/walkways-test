package com.tereshkov.walkways.data


data class BusSheduleEvents(
        var number_bus: Int,
        var from_station: String,
        var to_station: String,
        var time_start: String,
        var time_finish: String
) : Event {
    override fun getTime(): Int = 1
}