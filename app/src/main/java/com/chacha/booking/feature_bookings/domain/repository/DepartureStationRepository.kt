package com.chacha.booking.feature_bookings.domain.repository

import com.chacha.booking.core.utils.Resource
import com.chacha.booking.feature_bookings.data.local.DestinationsEntity


interface DepartureStationRepository {
//    suspend fun getDestinations():  Resource<List<DestinationsEntity>>
    suspend fun getAllDestination(): Resource<List<DestinationsEntity>>
    suspend fun searchDestination(params: String): Resource<List<DestinationsEntity>>


}