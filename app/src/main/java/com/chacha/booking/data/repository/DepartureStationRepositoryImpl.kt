package com.chacha.booking.data.repository

import com.chacha.booking.database.BookingsDatabase
import com.chacha.booking.utils.Resource
import com.chacha.booking.data.local.entity.DestinationsEntity
import com.chacha.booking.domain.repository.DepartureStationRepository
import timber.log.Timber
import javax.inject.Inject

class DepartureStationRepositoryImpl @Inject constructor(
    private val database: BookingsDatabase
) : DepartureStationRepository {
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