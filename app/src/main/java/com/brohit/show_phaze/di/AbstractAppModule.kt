package com.brohit.show_phaze.di

import com.brohit.show_phaze.data.repository.AuthRepositoryImpl
import com.brohit.show_phaze.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class AbstractAppModule {

    @Singleton
    @Binds
    abstract fun providesAbstractRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

}