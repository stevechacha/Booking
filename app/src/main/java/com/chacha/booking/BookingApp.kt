package com.chacha.booking

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookingApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}