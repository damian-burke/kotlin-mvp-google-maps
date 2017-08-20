package com.brainasaservice.kotlin.kotlingooglemaps.database

import com.brainasaservice.kotlin.kotlingooglemaps.model.ScooterInfo
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Damian on 20.08.2017.
 */

interface Database {
    fun getScooters() : Single<List<ScooterInfo>>
    fun getScooters(bounds: LatLngBounds) : Single<List<ScooterInfo>>
}