package com.tereshkov.walkways.di

import android.content.Context
import com.tereshkov.walkways.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidSupportInjectionModule::class, ActivityModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}