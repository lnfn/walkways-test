package com.tereshkov.walkways.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tereshkov.walkways.Screens
import com.tereshkov.walkways.data.BusSheduleEvents
import com.tereshkov.walkways.data.repositories.IEventsTimelineRepository
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainActivityFragmentViewModel @Inject constructor(
        private val router: Router,
        private val eventsTimelineRepository: IEventsTimelineRepository
) : ViewModel() {
    val events = MutableLiveData<List<BusSheduleEvents>>()

    fun loadEvents() {
        val list = mutableListOf<BusSheduleEvents>()
        list.add(BusSheduleEvents(102, "Апатиты", "Кировск", "12:00", "13:00"))
        list.add(BusSheduleEvents(102, "Апатиты", "Кировск", "12:00", "13:00"))
        list.add(BusSheduleEvents(102, "Апатиты", "Кировск", "12:00", "13:00"))
        list.add(BusSheduleEvents(102, "Апатиты", "Кировск", "12:00", "13:00"))
        events.value = list
    }

    fun goToFragmentSubMenu() {
        router.navigateTo(Screens.SUB_MENU_SCREEN)
    }
}