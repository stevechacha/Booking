package com.chacha.presentation.booking.bus_repository

import com.chacha.presentation.booking.model.BusBooking
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// ApiService.kt
interface ApiService {

     @POST("book_bus")
     suspend fun bookBus(@Body bookingDetails: BusBooking): Response<BusBooking>

     @GET("search_cities")
     suspend fun searchCities(@Query("query") query: String): Response<List<String>>
}

