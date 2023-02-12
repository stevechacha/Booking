package com.chacha.booking.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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