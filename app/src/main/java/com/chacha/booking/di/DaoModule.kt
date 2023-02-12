package com.chacha.booking.di

import com.chacha.booking.database.BookingsDatabase
import com.chacha.booking.data.local.dao.DestinationsDao
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