package com.nunopeixoto.leboncointest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nunopeixoto.leboncointest.data.local.dao.AlbumDao
import com.nunopeixoto.leboncointest.data.mapper.toAlbumEntity
import com.nunopeixoto.leboncointest.data.mapper.toAlbumUiModel
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the [AlbumLocalDatabaseRepository] interface.
 *
 * @param albumDao The Data Access Object (DAO) for interacting with the "albums" table in the database.
 */
class AlbumLocalDatabaseRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao
) : AlbumLocalDatabaseRepository {

    override suspend fun saveAlbums(albumsDto: List<AlbumDto>) {
        val albumEntities = albumsDto.map { it.toAlbumEntity() }
        albumDao.clearAndInsertAll(albumEntities)
    }

    override fun getAllAlbums(): Flow<PagingData<AlbumUiModel>> {
        // Configuration for pagination, specifying the page size and disabling placeholders.
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Number of items to load per page.
                enablePlaceholders = false
            ),
            // Provides the PagingSource from the DAO to fetch data from the local database.
            pagingSourceFactory = { albumDao.getAllAlbumEntities() }
        ).flow
        // Maps the [PagingData<AlbumEntity>] to [PagingData<AlbumUiModel>] using the extension function.
            .map { pagingData ->
            pagingData.map { it.toAlbumUiModel() }
        }
    }
}