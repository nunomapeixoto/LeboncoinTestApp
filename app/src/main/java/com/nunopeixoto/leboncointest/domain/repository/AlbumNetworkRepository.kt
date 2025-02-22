package com.nunopeixoto.leboncointest.domain.repository

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto

/**
 * Interface defining the contract for fetching album data from a remote network source.
 * This repository is responsible for retrieving album data from an external API or service.
 */
interface AlbumNetworkRepository {

    /**
     * Fetches a list of albums from the remote network source.
     *
     * @return A list of [AlbumDto] objects representing the albums retrieved from the network.
     */
    suspend fun getAlbumsDto(): List<AlbumDto>
}