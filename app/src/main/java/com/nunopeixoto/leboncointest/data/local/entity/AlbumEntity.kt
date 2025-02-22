package com.nunopeixoto.leboncointest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an album entity stored in the local database.
 * This class defines the structure of the "albums" table, including its columns and primary key.
 */
@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)