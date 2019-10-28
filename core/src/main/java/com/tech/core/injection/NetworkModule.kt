package com.tech.core.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tech.core.BASE_URL
import com.tech.core.api.EngineersApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesOkhttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideEngineersApiClient(retrofit: Retrofit) = retrofit.create(EngineersApiClient::class.java)
}