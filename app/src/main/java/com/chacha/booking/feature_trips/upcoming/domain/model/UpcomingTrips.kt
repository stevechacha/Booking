package com.chacha.booking.feature_trips.upcoming.domain.model

import com.google.gson.annotations.SerializedName

data class UpcomingTrips(
    @SerializedName("address_2")
    val address2: Any,
    @SerializedName("address_3")
    val address3: Any,
    @SerializedName("brewery_type")
    val breweryType: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("county_province")
    val countyProvince: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("postal_code")
    val postalCode: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("website_url")
    val websiteUrl: String
)
