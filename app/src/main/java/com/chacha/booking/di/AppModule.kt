package com.chacha.booking.di

import com.chacha.booking.core.data.local.BookingsDatabase
import com.chacha.booking.feature_auth.login.data.repository.LoginRepositoryImpl
import com.chacha.booking.feature_auth.login.domain.repository.LoginRepository
import com.chacha.booking.feature_auth.signup.data.repository.SignUpRepositoryImpl
import com.chacha.booking.feature_auth.signup.domain.repository.SignUpRepository
import com.chacha.booking.feature_bookings.data.repository.DepartureStationRepositoryImpl
import com.chacha.booking.feature_bookings.data.repository.DestinationStationRepositoryImpl
import com.chacha.booking.feature_bookings.domain.repository.DepartureStationRepository
import com.chacha.booking.feature_bookings.domain.repository.DestinationStationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDestinationRepository(
        database: BookingsDatabase
    ): DestinationStationRepository {
        return DestinationStationRepositoryImpl(
            database
        )
    }

    @Provides
    @Singleton
    fun provideDepartureRepository(
        database: BookingsDatabase
    ): DepartureStationRepository {
        return DepartureStationRepositoryImpl(
            database
        )
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideLoginRepository(
        firebaseAuth: FirebaseAuth
    ): LoginRepository{
        return LoginRepositoryImpl(
            firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(
        firebaseAuth: FirebaseAuth,
    ): SignUpRepository {
        return SignUpRepositoryImpl(
            firebaseAuth,
        )
    }


}