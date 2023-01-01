package com.chacha.booking.di

import com.chacha.booking.core.data.local.BookingsDatabase
import com.chacha.booking.feature_bookings.data.repository.DepartureStationRepositoryImpl
import com.chacha.booking.feature_bookings.domain.repository.DepartureStationRepository
import com.chacha.booking.feature_bookings.domain.repository.DestinationStationRepository
import com.chacha.booking.feature_bookings.data.repository.DestinationStationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {
    /*@Binds
    @Singleton
    abstract fun provideDestinationRepository(
        destinationStationRepositoryImpl: DestinationStationRepositoryImpl
    ): DestinationStationRepository

    @Binds
    @Singleton
    abstract fun provideDepartureRepository(
        destinationStationRepositoryImpl: DestinationStationRepositoryImpl
    ): DepartureStationRepository*/

}