package com.tereshkov.walkways.di

import com.tereshkov.walkways.data.repositories.EventsTimelineRepository
import com.tereshkov.walkways.data.repositories.IEventsTimelineRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RepositoriesModule {
    @Binds
    @Singleton
    fun eventsTimelineRepository(repo: EventsTimelineRepository): IEventsTimelineRepository
}