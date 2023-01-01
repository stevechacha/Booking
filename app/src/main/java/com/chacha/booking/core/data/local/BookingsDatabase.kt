package com.chacha.booking.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chacha.booking.feature_bookings.data.local.DestinationsDao
import com.chacha.booking.feature_bookings.data.local.DestinationsEntity

@Database(
    entities = [DestinationsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookingsDatabase : RoomDatabase() {
    abstract val destinationsDao: DestinationsDao
}