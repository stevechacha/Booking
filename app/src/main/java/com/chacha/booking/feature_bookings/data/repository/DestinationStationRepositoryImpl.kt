package com.chacha.booking.feature_bookings.data.repository

import com.chacha.booking.core.data.local.BookingsDatabase
import com.chacha.booking.core.utils.Resource
import com.chacha.booking.feature_bookings.data.local.DestinationsEntity
import com.chacha.booking.feature_bookings.domain.repository.DestinationStationRepository
import timber.log.Timber

class DestinationStationRepositoryImpl(
    private val database: BookingsDatabase
) : DestinationStationRepository {

    override suspend fun getAllDestination(): Resource<List<DestinationsEntity>> {
        return  try {
            val destinationDb = database.destinationsDao.getAllDestination()
            Resource.Success<List<DestinationsEntity>>(data = destinationDb)

        } catch (e: Exception){
            Timber.e(message = e.localizedMessage ?: " An Error Occured")
            Resource.Error<List<DestinationsEntity>>( message = e.localizedMessage?: "An Error Occured")
        }
    }

    override suspend fun searchDestination(params: String): Resource<List<DestinationsEntity>> {
        return try {
            val result = database.destinationsDao.searchDestination(params)
            Resource.Success<List<DestinationsEntity>>(data = result)
        } catch (e: Exception){

            Resource.Error<List<DestinationsEntity>>(message = e.localizedMessage ?: "Could not find the destination")
        }

    }
}