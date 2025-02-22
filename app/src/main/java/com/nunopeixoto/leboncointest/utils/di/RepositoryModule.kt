package com.nunopeixoto.leboncointest.utils.di

import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import com.nunopeixoto.leboncointest.data.repository.AlbumLocalDatabaseRepositoryImpl
import com.nunopeixoto.leboncointest.domain.repository.AlbumNetworkRepository
import com.nunopeixoto.leboncointest.data.repository.AlbumNetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Hilt module that provides repository dependencies for the application.
 * This module is installed in the [SingletonComponent] to ensure that the provided
 * dependencies have a singleton scope and are shared across the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumNetworkRepository(
        albumNetworkRepository: AlbumNetworkRepositoryImpl
    ): AlbumNetworkRepository {
        return albumNetworkRepository
    }

    @Provides
    @Singleton
    fun provideAlbumLocalDatabaseRepository(
        albumLocalDatabaseRepository: AlbumLocalDatabaseRepositoryImpl
    ): AlbumLocalDatabaseRepository {
        return albumLocalDatabaseRepository
    }
}