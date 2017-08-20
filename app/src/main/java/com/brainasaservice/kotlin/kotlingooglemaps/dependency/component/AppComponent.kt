package com.brainasaservice.kotlin.kotlingooglemaps.dependency.component

import com.brainasaservice.kotlin.kotlingooglemaps.dependency.module.*
import com.brainasaservice.kotlin.kotlingooglemaps.ui.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Damian on 20.08.2017.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        InjectorsModule::class,
        ServiceModule::class,
        AppModule::class,
        PresenterModule::class,
        DependencyModule::class
))
interface AppComponent : AndroidInjector<App>
