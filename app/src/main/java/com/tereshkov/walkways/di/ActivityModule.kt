package com.tereshkov.walkways.di

import com.tereshkov.walkways.di.scopes.ActivityScope
import com.tereshkov.walkways.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    fun mainActivityInjector(): MainActivity
}