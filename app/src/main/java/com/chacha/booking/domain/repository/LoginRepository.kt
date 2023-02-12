package com.chacha.booking.domain.repository

import com.chacha.booking.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    fun logout()
}