package com.brainasaservice.kotlin.kotlingooglemaps.model

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Damian on 20.08.2017.
 */

data class Location(
        val lat: Double,
        val lng: Double
) {
    fun asLatLong(): LatLng {
        return LatLng(lat, lng)
    }
}