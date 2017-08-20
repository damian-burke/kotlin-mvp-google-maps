package com.brainasaservice.kotlin.kotlingooglemaps.dependency.module

import com.brainasaservice.kotlin.kotlingooglemaps.BuildConfig
import com.brainasaservice.kotlin.kotlingooglemaps.net.ScooterService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Damian on 20.08.2017.
 */

@Module
class ServiceModule() {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SCOOTER_API_URI)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    fun provideScooterService(retrofit: Retrofit): ScooterService {
        return retrofit.create(ScooterService::class.java)
    }
}