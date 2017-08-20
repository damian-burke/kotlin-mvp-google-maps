package com.brainasaservice.kotlin.kotlingooglemaps.ui.map

import com.brainasaservice.kotlin.kotlingooglemaps.ui.BaseContract
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/**
 * Created by Damian on 20.08.2017.
 */
class MapsContract {
    interface View : BaseContract.View {
        /**
         * Add scooter to the view
         */
        fun addScooter(id: String, licensePlate: String, location: LatLng, color: Float, distance: Double, battery: Int)

        /**
         * Scroll the map to certain boundaries (might be center of data)
         */
        fun scrollMapTo(bounds: LatLngBounds)

        /**
         * Scooter list battery statistic data
         */
        fun displayBatteryStatistic(fullBattery: Int, mediumBattery: Int, lowBattery: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         * Trigger map data initialization
         */
        fun onMapReady()

        /**
         * Trigger scooter-clicked flow
         */
        fun clickScooter(id: String)

        /**
         * Inform presenter of changed map boundaries
         */
        fun setMapBoundaries(bounds: LatLngBounds)

        /**
         * Trigger dataset refresh
         */
        fun clickRefresh()
    }
}
