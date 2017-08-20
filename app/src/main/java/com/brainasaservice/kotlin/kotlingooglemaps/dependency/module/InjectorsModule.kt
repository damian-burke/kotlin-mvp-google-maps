package com.brainasaservice.kotlin.kotlingooglemaps.dependency.module

import com.brainasaservice.kotlin.kotlingooglemaps.ui.map.MapsActivity
import com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen.SplashscreenActivity
import com.brainasaservice.lawful.dependency.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Damian on 20.08.2017.
 */

@Module
abstract class InjectorsModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mapActivity(): MapsActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashscreenActivity(): SplashscreenActivity

}