package com.tereshkov.walkways.di

import com.tereshkov.walkways.di.scopes.FragmentScope
import com.tereshkov.walkways.ui.ManyUseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Tereshkov on 05.10.2017.
 */
@Module
interface SingleActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun manyUseFragmentInjector(): ManyUseFragment
}