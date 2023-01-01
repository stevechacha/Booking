package com.chacha.booking.feature_auth.signup.domain.repository

import com.chacha.booking.core.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface SignUpRepository {
    val currentUser: FirebaseUser?
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>

}