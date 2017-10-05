package com.tereshkov.walkways.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class, NavigationModule::class, ViewModelModule::class,
        RepositoriesModule::class))
class AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE)
}