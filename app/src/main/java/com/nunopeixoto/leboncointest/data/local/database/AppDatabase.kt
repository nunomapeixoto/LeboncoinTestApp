package com.nunopeixoto.leboncointest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nunopeixoto.leboncointest.data.local.dao.AlbumDao
import com.nunopeixoto.leboncointest.data.local.entity.AlbumEntity

/**
 * Abstract class representing the application's Room database.
 * This class serves as the main access point to the persisted data and provides
 * methods to retrieve DAOs for interacting with the database.
 *
 * The database contains a table for [AlbumEntity].
 */
@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}