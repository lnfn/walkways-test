package com.tereshkov.walkways.di

import com.tereshkov.walkways.di.scopes.ActivityScope
import com.tereshkov.walkways.ui.MainActivity
import com.tereshkov.walkways.ui.SingleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SingleActivityModule::class))
    fun singleActivityInjector(): SingleActivity
}