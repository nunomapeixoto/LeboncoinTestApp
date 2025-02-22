package com.nunopeixoto.leboncointest.domain.model

/**
 * Represents an album with an id, title and thumbnail URL.
 *
 * @property id The unique identifier for the album.
 * @property title The title of the album.
 * @property thumbnailUrl The URL of the album's thumbnail image.
 */
data class AlbumUiModel(
    val id: Int,
    val title: String,
    val thumbnailUrl: String
)