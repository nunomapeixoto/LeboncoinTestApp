package com.nunopeixoto.leboncointest.domain.usecase

import androidx.paging.PagingData
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case responsible for retrieving albums from the local database.
 * This class encapsulates the logic for fetching paginated album data from the local database
 * and providing it as a stream of [PagingData] for the UI layer.
 *
 * @param albumLocalDatabaseRepository The repository interface for accessing album data from the local database.
 */
class GetAlbumsUseCase @Inject constructor(
    private val albumLocalDatabaseRepository: AlbumLocalDatabaseRepository
) {

    /**
     * Executes the use case to retrieve albums from the local database.
     * This function is marked as an operator function, allowing it to be invoked like a function call.
     *
     * @return A [Flow] of [PagingData] containing [AlbumUiModel] objects.
     */
    operator fun invoke(): Flow<PagingData<AlbumUiModel>> {
        return albumLocalDatabaseRepository.getAllAlbums()
    }
}