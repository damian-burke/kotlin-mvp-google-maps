package com.brainasaservice.kotlin.kotlingooglemaps.ui.map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brainasaservice.kotlin.kotlingooglemaps.R
import com.brainasaservice.kotlin.kotlingooglemaps.ui.BaseActivity
import com.brainasaservice.kotlin.kotlingooglemaps.util.onLayoutChange

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import timber.log.Timber
import java.util.*

class MapsActivity : BaseActivity<MapsContract.View, MapsContract.Presenter>(), MapsContract.View, OnMapReadyCallback {
    var mapReady: Boolean = false

    var mapLayoutReady: Boolean = false

    val markerMap = WeakHashMap<String, Marker?>()

    lateinit var mapFragment: SupportMapFragment

    lateinit var mMap: GoogleMap

    override val contentLayout: Int = R.layout.activity_maps

    override fun displayBatteryStatistic(fullBattery: Int, mediumBattery: Int, lowBattery: Int) {
        Timber.d("displayBatteryStatistic(fullBattery=%d, mediumBattery=%d, lowBattery=%d)", fullBattery, mediumBattery, lowBattery)
        textGreen.post {
            textGreen.text = getString(R.string.scooter_debug_high_battery, fullBattery)
            textYellow.text = getString(R.string.scooter_debug_medium_battery, mediumBattery)
            textRed.text = getString(R.string.scooter_debug_low_battery, lowBattery)
        }
    }

    override fun scrollMapTo(bounds: LatLngBounds) {
        Timber.d("scrollMapTo(mapReady=%b, mapLayoutReady=%b)", mapReady, mapLayoutReady)
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 32))
    }

    override fun addScooter(id: String, licensePlate: String, location: LatLng, color: Float, distance: Double, battery: Int) {
        Timber.d("addScooter(id=%s, location=%s, drivingRangeKm=%f, battery=%d)", id, location, distance, battery)
        // check if marker is already on the map
        val marker = markerMap.getOrPut(id, {
            mMap.addMarker(MarkerOptions()
                    .position(location)
                    .title("Scooter ${licensePlate}"))
        })

        marker?.setSnippet(getString(R.string.map_scooter_snippet, String.format(Locale.getDefault(), "%.2f", distance), battery))
        marker?.setIcon(BitmapDescriptorFactory.defaultMarker(color))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttonRefresh.onClick {
            presenter.clickRefresh()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapReady = true
        mMap = googleMap
        mMap.setOnCameraIdleListener {
            // tell presenter our map has been moved, refresh data set according to it
            val bounds = mMap.projection.visibleRegion.latLngBounds
            presenter.setMapBoundaries(bounds)
        }
        mapFragment.view?.onLayoutChange {
            mapLayoutReady = true
            presenter.onMapReady()
        }
    }


}
