package com.nunopeixoto.leboncointest.utils.di

import android.content.Context
import androidx.room.Room
import com.nunopeixoto.leboncointest.data.local.dao.AlbumDao
import com.nunopeixoto.leboncointest.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Hilt module that provides database-related dependencies for the application.
 * This module is installed in the [SingletonComponent] to ensure that the provided
 * dependencies have a singleton scope and are shared across the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.albumDao()
    }
}