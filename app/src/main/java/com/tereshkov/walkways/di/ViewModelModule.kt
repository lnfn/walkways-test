package com.tereshkov.walkways.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tereshkov.walkways.viewmodels.MainActivityFragmentViewModel
import com.tereshkov.walkways.viewmodels.WalkwaysViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityFragmentViewModel::class)
    fun bindUserViewModel(viewModel: MainActivityFragmentViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: WalkwaysViewModelFactory): ViewModelProvider.Factory
}