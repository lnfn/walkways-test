package com.tereshkov.walkways.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import com.tereshkov.walkways.data.services.WalkwaysService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {
    @Singleton
    @Provides
    internal fun provideWalkwaysService(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl("http://192.168.0.10/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(WalkwaysService::class.java)

    @Provides
    internal fun provideOkHttpClient(context: Context, httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ChuckInterceptor(context).showNotification(false))
            .build()

    @Provides
    internal fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    internal fun provideGson() = GsonBuilder()
            .excludeFieldsWithModifiers(FINAL, TRANSIENT, STATIC)
            .serializeNulls()
            .create()
}