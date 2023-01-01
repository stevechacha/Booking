package com.chacha.booking.feature_auth.login.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @POST("login")
    suspend fun login(
        @Body login: Login
    ): Response<Login>
    data class Login(
        val PhoneNumber: String?,
        val Email: String?,
        val Password: String
    )

    @POST("login")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") Email: String?,
        @Field("password") Password: String,
        @Field("phoneNumber") PhoneNumber: String?
    )
}