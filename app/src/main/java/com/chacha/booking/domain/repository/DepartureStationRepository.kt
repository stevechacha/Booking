package com.chacha.booking.domain.repository

import com.chacha.booking.utils.Resource
import com.chacha.booking.data.local.entity.DestinationsEntity


interface DepartureStationRepository {
//    suspend fun getDestinations():  Resource<List<DestinationsEntity>>
    suspend fun getAllDestination(): Resource<List<DestinationsEntity>>
    suspend fun searchDestination(params: String): Resource<List<DestinationsEntity>>


}