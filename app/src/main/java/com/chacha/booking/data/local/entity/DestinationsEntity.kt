package com.chacha.booking.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class DestinationsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val fromCity: String,
    val fromCityStation: String
)
