package com.tereshkov.walkways.viewmodels

import com.tereshkov.walkways.FieldWrapper
import com.tereshkov.walkways.data.repositories.IEventsTimelineRepository
import com.tereshkov.walkways.di.qualifiers.DetailId
import javax.inject.Inject

/**
 * Created by Tereshkov on 09.10.2017.
 */
class DetailViewModel @Inject constructor(
        private val eventsTimelineRepository: IEventsTimelineRepository,
        @DetailId private val itemId: FieldWrapper<Int>
) {

    fun takeId() = itemId.value
}