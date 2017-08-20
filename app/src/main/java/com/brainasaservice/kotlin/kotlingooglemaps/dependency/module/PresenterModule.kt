package com.brainasaservice.kotlin.kotlingooglemaps.dependency.module

import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import com.brainasaservice.kotlin.kotlingooglemaps.ui.map.MapsContract
import com.brainasaservice.kotlin.kotlingooglemaps.ui.map.MapsPresenter
import com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen.SplashscreenContract
import com.brainasaservice.kotlin.kotlingooglemaps.ui.splashscreen.SplashscreenPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Damian on 20.08.2017.
 */

@Module
class PresenterModule {
    @Singleton
    @Provides
    fun mapsPresenter(dep: Dependency) : MapsContract.Presenter {
        return MapsPresenter(dep)
    }

    @Singleton
    @Provides
    fun splashscreenPresenter(dep: Dependency) : SplashscreenContract.Presenter {
        return SplashscreenPresenter(dep)
    }
}