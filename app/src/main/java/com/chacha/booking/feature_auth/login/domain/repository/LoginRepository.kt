package com.chacha.booking.feature_auth.login.domain.repository

import com.chacha.booking.core.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    fun logout()
}