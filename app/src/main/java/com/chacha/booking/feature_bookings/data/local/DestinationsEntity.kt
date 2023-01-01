package com.chacha.booking.feature_bookings.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class DestinationsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val fromCity: String,
    val fromCityStation: String
)
