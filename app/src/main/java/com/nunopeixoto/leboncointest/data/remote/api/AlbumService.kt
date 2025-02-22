package com.nunopeixoto.leboncointest.data.remote.api

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import retrofit2.http.GET

/**
 * Interface defining the contract for interacting with the remote album service.
 */
interface AlbumService {

    /**
     * Fetches a list of albums from the remote API.
     *
     * @return A list of [AlbumDto] objects representing the albums retrieved from the API.
     */
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>
}