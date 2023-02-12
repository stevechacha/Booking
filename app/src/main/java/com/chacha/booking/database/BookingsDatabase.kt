package com.chacha.booking.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chacha.booking.data.local.dao.DestinationsDao
import com.chacha.booking.data.local.entity.DestinationsEntity

@Database(
    entities = [DestinationsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookingsDatabase : RoomDatabase() {
    abstract val destinationsDao: DestinationsDao
}