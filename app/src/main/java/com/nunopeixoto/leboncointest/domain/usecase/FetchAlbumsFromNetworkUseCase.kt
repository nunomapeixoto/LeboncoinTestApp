package com.nunopeixoto.leboncointest.domain.usecase

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.repository.AlbumNetworkRepository
import com.nunopeixoto.leboncointest.utils.network.NetworkResult
import javax.inject.Inject

/**
 * A use case responsible for fetching albums from a remote network source.
 * This class encapsulates the logic for retrieving album data from the network
 * and handling potential errors, returning the result as a [NetworkResult].
 *
 * @param albumNetworkRepository The repository interface for accessing album data from the network.
 */
class FetchAlbumsFromNetworkUseCase @Inject constructor(
    private val albumNetworkRepository: AlbumNetworkRepository
) {

    /**
     * Executes the use case to fetch albums from the network.
     * This function is marked as an operator function, allowing it to be invoked like a function call.
     *
     * @return A [NetworkResult] containing either the list of [AlbumDto] objects on success
     * or an error message on failure.
     */
    suspend operator fun invoke(): NetworkResult<List<AlbumDto>> {
        try {
            val result = albumNetworkRepository.getAlbumsDto()
            return NetworkResult.Success(result)
        }  catch (e: Exception) {
            e.printStackTrace()
            return NetworkResult.Error("Unknown error")
        }
    }
}