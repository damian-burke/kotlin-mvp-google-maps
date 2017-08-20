package com.brainasaservice.kotlin.kotlingooglemaps.dependency.module

import com.brainasaservice.kotlin.kotlingooglemaps.database.OnlineDatabase
import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import com.brainasaservice.kotlin.kotlingooglemaps.model.ScooterResponse
import com.brainasaservice.kotlin.kotlingooglemaps.net.ScooterService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Damian on 20.08.2017.
 */
@Module
class DependencyModule {
    @Provides
    @Singleton
    fun providesDependency(scooterService: ScooterService): Dependency {
        return Dependency(OnlineDatabase(scooterService))
    }
}
