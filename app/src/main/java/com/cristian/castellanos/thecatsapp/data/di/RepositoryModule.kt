package com.cristian.castellanos.thecatsapp.data.di

import com.cristian.castellanos.thecatsapp.data.CatRepository
import com.cristian.castellanos.thecatsapp.data.CatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(
        catRepositoryImpl: CatRepositoryImpl
    ): CatRepository
}