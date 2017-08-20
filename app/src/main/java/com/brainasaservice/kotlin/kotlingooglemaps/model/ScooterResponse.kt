package com.brainasaservice.kotlin.kotlingooglemaps.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Damian on 20.08.2017.
 */

data class ScooterResponse(
        val meta: ResponseMeta,
        val data: ScooterListWrapper
) {
    fun getResponseTime(): Date {
        return meta.date
    }

    fun getScooters(): List<ScooterInfo> {
        return data.scooters
    }
}

data class ResponseMeta(
        @SerializedName("server_time") val date: Date,
        val status: Int,
        val key: String
)

data class ScooterListWrapper(
        val scooters: List<ScooterInfo>
)