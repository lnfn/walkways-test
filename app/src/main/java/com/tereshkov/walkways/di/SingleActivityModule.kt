package com.tereshkov.walkways.di

import com.tereshkov.walkways.di.scopes.FragmentScope
import com.tereshkov.walkways.ui.DetailFragment
import com.tereshkov.walkways.ui.LocalContainerFragment
import com.tereshkov.walkways.ui.LocalFragment
import com.tereshkov.walkways.ui.ManyUseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Tereshkov on 05.10.2017.
 */
@Module(includes = arrayOf(MainActivityModule::class))
interface SingleActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun manyUseFragmentInjector(): ManyUseFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun lcalFragmentInjector(): LocalFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(DetailFragmentModule::class))
    fun detailFragmentInjector(): DetailFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun localContainerFragmentInjector(): LocalContainerFragment
}