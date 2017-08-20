package com.brainasaservice.kotlin.kotlingooglemaps.ui

import android.app.Activity
import android.support.v4.app.Fragment
import com.brainasaservice.kotlin.kotlingooglemaps.BuildConfig
import com.brainasaservice.kotlin.kotlingooglemaps.dependency.component.DaggerAppComponent
import com.brainasaservice.kotlin.kotlingooglemaps.dependency.module.AppModule
import com.brainasaservice.kotlin.kotlingooglemaps.dependency.module.DependencyModule
import com.brainasaservice.kotlin.kotlingooglemaps.dependency.module.PresenterModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Damian on 20.08.2017.
 */

class App : DaggerApplication() {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        when(BuildConfig.BUILD_TYPE) {
            "debug" -> {
                Timber.plant(Timber.DebugTree())
            }
            "release" -> {
                // initialize analytics, logging, etc for release
            }
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .dependencyModule(DependencyModule())
                .presenterModule(PresenterModule())
                .build()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}