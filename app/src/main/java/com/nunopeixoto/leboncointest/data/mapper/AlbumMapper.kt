package com.nunopeixoto.leboncointest.data.mapper

import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.data.local.entity.AlbumEntity
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel

/**
 * Converts an [AlbumEntity] object to an [AlbumUiModel] object.
 * This is used to transform data from the database layer into a format
 * for presentation in the UI layer.
 *
 * @return An [AlbumUiModel] object containing the data for UI display.
 */
fun AlbumEntity.toAlbumUiModel(): AlbumUiModel {
    return AlbumUiModel(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.thumbnailUrl
    )
}

/**
 * Converts an [AlbumDto] object to an [AlbumEntity] object.
 * This is used to transform data from the network layer into a format
 * for storage in the local database.
 *
 * @return An [AlbumEntity] object containing the data for database insertion.
 */
fun AlbumDto.toAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        id = this.id,
        albumId = this.albumId,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}
