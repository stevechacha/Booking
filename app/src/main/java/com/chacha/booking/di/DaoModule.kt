package com.chacha.booking.di

import com.chacha.booking.core.data.local.BookingsDatabase
import com.chacha.booking.feature_bookings.data.local.DestinationsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    @Singleton
    fun provideDestinationDao(
        database: BookingsDatabase
    ): DestinationsDao = database.destinationsDao
}