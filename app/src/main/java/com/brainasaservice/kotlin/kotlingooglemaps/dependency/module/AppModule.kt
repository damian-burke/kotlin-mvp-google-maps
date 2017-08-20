package com.brainasaservice.kotlin.kotlingooglemaps.dependency.module

import android.content.Context
import android.content.SharedPreferences
import com.brainasaservice.kotlin.kotlingooglemaps.ui.App
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

/**
 * Created by Damian on 20.08.2017.
 */

@Module
class AppModule(val application: App) {

    @Provides
    @Singleton
    fun providesApplication(): App {
        return application
    }

    @Provides
    @Singleton
    fun providesContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.defaultSharedPreferences
    }
}