package com.brainasaservice.kotlin.kotlingooglemaps.model

import com.brainasaservice.kotlin.kotlingooglemaps.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

/**
 * Created by Damian on 20.08.2017.
 */

data class ScooterInfo(
        val id : String,
        val vin: String,
        val model: String,
        @SerializedName("market_id") val marketId: String,
        @SerializedName("license_plate") val licensePlate: String,
        @SerializedName("energy_level") val energyLevel: Int,
        @SerializedName("distance_to_travel") val drivingRangeKm: Double,
        val location: Location
) {
    fun batteryLevelMarkerHue() : Float {
        return when(energyLevel) {
            in 0..30 -> BitmapDescriptorFactory.HUE_RED
            in 31..50 -> BitmapDescriptorFactory.HUE_YELLOW
            else -> BitmapDescriptorFactory.HUE_GREEN
        }
    }
}