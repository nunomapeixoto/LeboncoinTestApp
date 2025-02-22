package com.nunopeixoto.leboncointest.domain.usecase

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import javax.inject.Inject

/**
 * A use case responsible for saving albums to the local database.
 * This class encapsulates the logic for persisting a list of albums in the local database.
 *
 * @param albumLocalDatabaseRepository The repository interface for accessing and managing album data in the local database.
 */
class SaveAlbumsUseCase @Inject constructor(
    private val albumLocalDatabaseRepository: AlbumLocalDatabaseRepository
) {
    suspend operator fun invoke(albumsDto: List<AlbumDto>) {
        return albumLocalDatabaseRepository.saveAlbums(albumsDto)
    }
}