package com.tereshkov.walkways.di

import android.content.Context
import android.content.SharedPreferences
import com.tereshkov.walkways.APP_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Module(
        includes = arrayOf(
                AndroidSupportInjectionModule::class,
                ActivityModule::class,
                NetworkModule::class,
                NavigationModule::class,
                LocalNavigationModule::class,
                ViewModelModule::class,
                RepositoriesModule::class
        )
)
class AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)
}