package com.brainasaservice.kotlin.kotlingooglemaps.net

import com.brainasaservice.kotlin.kotlingooglemaps.model.ScooterResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Damian on 20.08.2017.
 */

interface ScooterService {
    @GET("scooters.json") // extract into constant
    fun getScooters() : Single<ScooterResponse>
}