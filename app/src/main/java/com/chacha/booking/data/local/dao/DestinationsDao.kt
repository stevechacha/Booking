package com.chacha.booking.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chacha.booking.data.local.entity.DestinationsEntity

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