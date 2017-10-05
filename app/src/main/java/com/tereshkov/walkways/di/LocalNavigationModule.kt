package com.tereshkov.walkways.di

import com.tereshkov.walkways.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Tereshkov on 01.10.2017.
 */
@Module
class LocalNavigationModule {
    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): LocalCiceroneHolder {
        return LocalCiceroneHolder()
    }
}