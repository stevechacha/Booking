package com.chacha.booking.domain.repository

import com.chacha.booking.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface SignUpRepository {
    val currentUser: FirebaseUser?
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>

}