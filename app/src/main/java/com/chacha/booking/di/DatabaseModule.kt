package com.chacha.booking.di

import android.app.Application
import androidx.room.Room
import com.chacha.booking.database.BookingsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFinanceDatabase(application: Application): BookingsDatabase {
        return  Room.databaseBuilder(
            application.applicationContext,
            BookingsDatabase::class.java,
            "booking_db"
        ).build()
    }



}