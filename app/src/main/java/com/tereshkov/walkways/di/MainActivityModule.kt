package com.tereshkov.walkways.di

import com.tereshkov.walkways.di.scopes.FragmentScope
import com.tereshkov.walkways.ui.MainActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun mainActivityFragmentInjector(): MainActivityFragment
}