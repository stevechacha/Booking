package com.chacha.booking.feature_bookings.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DestinationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDestination(destinationsEntity: DestinationsEntity)

    @Update
    suspend fun updateDestination(destinationsEntity: DestinationsEntity)

    @Query("SELECT * FROM destinations")
    fun getAllDestination(): List<DestinationsEntity>

    @Query("SELECT * FROM destinations WHERE fromCity LIKE '%' || :params || '%' OR CAST(fromCity as String) LIKE :params")
    suspend fun searchDestination(params: String): List<DestinationsEntity>

}