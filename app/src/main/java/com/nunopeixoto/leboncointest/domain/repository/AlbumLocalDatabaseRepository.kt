package com.nunopeixoto.leboncointest.domain.repository

import androidx.paging.PagingData
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for accessing and managing album data in a local database.
 * This repository is responsible for saving and retrieving album data.
 */
interface AlbumLocalDatabaseRepository {

    /**
     * Saves a list of albums to the local database.
     *
     * @param albumsDto A list of [AlbumDto] objects representing the albums to be saved.
     */
    suspend fun saveAlbums(albumsDto: List<AlbumDto>)

    /**
     * Retrieves all albums from the local database as a stream of [PagingData].
     * The data is wrapped in a [Flow].
     *
     * @return A [Flow] of [PagingData] containing [AlbumUiModel] objects.
     */
    fun getAllAlbums(): Flow<PagingData<AlbumUiModel>>
}