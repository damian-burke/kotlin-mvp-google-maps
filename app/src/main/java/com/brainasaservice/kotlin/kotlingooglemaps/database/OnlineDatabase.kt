package com.brainasaservice.kotlin.kotlingooglemaps.database

import com.brainasaservice.kotlin.kotlingooglemaps.model.ScooterInfo
import com.brainasaservice.kotlin.kotlingooglemaps.net.ScooterService
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Single
import timber.log.Timber

/**
 * Created by Damian on 20.08.2017.
 */

class OnlineDatabase(val scooterService: ScooterService) : Database {

    /**
     * Requests scooter list from API, maps to inner list and applies a filter to the results.
     * @TODO: Don't use direct call to API as this wasted the original result.
     */
    override fun getScooters(bounds: LatLngBounds): Single<List<ScooterInfo>> {
        return scooterService.getScooters()
                .map { resp -> resp.getScooters() }
                .map { list ->
                    list.filter { scooter ->
                        bounds.contains(scooter.location.asLatLong())
                    }
                }
    }

    /**
     * Requests scooter list from API and maps it to inner list since we do not care about meta data at the moment.
     */
    override fun getScooters(): Single<List<ScooterInfo>> {
        return scooterService.getScooters()
                .map { resp -> resp.getScooters() }
    }

}