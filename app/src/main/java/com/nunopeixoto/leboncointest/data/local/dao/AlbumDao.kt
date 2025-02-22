package com.nunopeixoto.leboncointest.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nunopeixoto.leboncointest.data.local.entity.AlbumEntity

@Dao
interface AlbumDao {
    /**
     * Deletes all existing albums and then inserts the provided list of albums.
     * Transaction ensures atomicity.
     *
     * @param albums The list of [AlbumEntity] objects to insert after clearing the existing data.
     */
    @Transaction
    suspend fun clearAndInsertAll(albums: List<AlbumEntity>) {
        clearAll()
        insertAll(albums)
    }

    /**
     * Inserts a list of [AlbumEntity] objects into the database.
     * If a conflict occurs, the existing row will be replaced.
     *
     * @param albums The list of [AlbumEntity] objects to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumEntity>)

    /**
     * Deletes all albums from the database.
     */
    @Query("DELETE FROM albums")
    suspend fun clearAll()

    /**
     * Retrieves all albums from the database as a [PagingSource].
     * This is used to support pagination when querying the database.
     *
     * @return A [PagingSource] that provides paginated access to [AlbumEntity] objects.
     */
    @Query("SELECT * FROM albums")
    fun getAllAlbumEntities(): PagingSource<Int, AlbumEntity>
}