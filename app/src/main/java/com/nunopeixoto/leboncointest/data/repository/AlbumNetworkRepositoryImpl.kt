package com.nunopeixoto.leboncointest.data.repository

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.utils.di.IoDispatcher
import com.nunopeixoto.leboncointest.data.remote.api.AlbumService
import com.nunopeixoto.leboncointest.domain.repository.AlbumNetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Implementation of the [AlbumNetworkRepository] interface.
 *
 * @param albumService The service interface for interacting with the remote API.
 * @param ioDispatcher The [CoroutineDispatcher] used to perform network operations on a background thread.
 */
class AlbumNetworkRepositoryImpl @Inject constructor(
    private val albumService: AlbumService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AlbumNetworkRepository {

    /**
     * Fetches a list of albums from the remote network source.
     * This function uses the provided [ioDispatcher] to ensure the network call is performed
     * on a background thread, avoiding blocking the main thread.
     *
     * @return A list of [AlbumDto] objects representing the albums retrieved from the network.
     */
    override suspend fun getAlbumsDto(): List<AlbumDto> = withContext(ioDispatcher) {
        albumService.getAlbums()
    }
}