package com.brainasaservice.kotlin.kotlingooglemaps.ui.map

import com.brainasaservice.kotlin.kotlingooglemaps.model.Dependency
import com.brainasaservice.kotlin.kotlingooglemaps.model.ScooterInfo
import com.brainasaservice.kotlin.kotlingooglemaps.ui.BasePresenter
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Damian on 20.08.2017.
 */
class MapsPresenter(dependency: Dependency) : BasePresenter<MapsContract.View>(dependency), MapsContract.Presenter {
    /**
     * Lock for the dataset refresh call
     */
    var isRefreshing = false

    /**
     * Handle refresh button clicks from the UI
     */
    override fun clickRefresh() {
        Timber.d("clickRefresh()")
        refreshDataSet()
    }

    /**
     * Handle map creation properly
     */
    override fun onMapReady() {
        Timber.d("onMapReady()")
        // initialize berlin position.
        val bounds = LatLngBounds(LatLng(52.24346329972111, 13.109975159168243), LatLng(52.74797543306293, 13.67498617619276))
        view?.scrollMapTo(bounds)
        refreshDataSet()
    }

    /**
     * This method calls the database to retrieve the currently available scooter data.
     * Only one call can be triggered at the same time (controlled by isRefreshing).
     *
     * @TODO: Remove scooters that are not in the data-set anymore
     * @TODO: Move statistics calculation somewhere else, execute its view-call on UI thread
     */
    private fun refreshDataSet() {
        if(isRefreshing) {
            Timber.d("is already refreshing.")
            return
        }

        dep.database.getScooters()
                .doOnSubscribe { isRefreshing = true }
                .doOnEvent { list, error -> isRefreshing = false }
                .doOnSuccess { list ->
                    // calculate statistics
                    val groups: Map<Float, List<ScooterInfo>> = list.groupBy { scooter -> scooter.batteryLevelMarkerHue() }
                    val fullBattery = groups.getOrDefault(BitmapDescriptorFactory.HUE_GREEN, listOf()).size
                    val mediumBattery = groups.getOrDefault(BitmapDescriptorFactory.HUE_YELLOW, listOf()).size
                    val lowBattery = groups.getOrDefault(BitmapDescriptorFactory.HUE_RED, listOf()).size

                    view?.displayBatteryStatistic(fullBattery, mediumBattery, lowBattery)
                }
                .flatMapPublisher { list -> Flowable.fromIterable(list) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ scooter ->
                    view?.addScooter(scooter.id, scooter.licensePlate, scooter.location.asLatLong(), scooter.batteryLevelMarkerHue(), scooter.drivingRangeKm, scooter.energyLevel)
                }, { error ->
                    Timber.e(error, "Unable to get scooter list.")
                })
    }

    /**
     * Clicked on a scooter
     * @TODO: Do something
     */
    override fun clickScooter(id: String) {
        Timber.d("clickScooter(id=%s)", id)
    }

    /**
     * Map boundaries changed
     * @TODO: Request dataset for new boundaries
     */
    override fun setMapBoundaries(bounds: LatLngBounds) {
        Timber.d("setMapBoundaries(bounds=%s)", bounds)

    }
}