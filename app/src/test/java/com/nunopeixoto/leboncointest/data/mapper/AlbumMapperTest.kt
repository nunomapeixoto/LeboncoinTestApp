package com.nunopeixoto.leboncointest.data.mapper

import com.nunopeixoto.leboncointest.data.local.entity.AlbumEntity
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlbumMapperTest {

    @Test
    fun `toAlbumUiModel - when given valid AlbumEntity - should return correct AlbumUiModel`() {
        // Arrange
        val albumEntity = AlbumEntity(
            id = 1,
            albumId = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbnail"
        )

        // Act
        val albumUiModel = albumEntity.toAlbumUiModel()

        // Assert
        assertThat(albumUiModel.id).isEqualTo(1)
        assertThat(albumUiModel.title).isEqualTo("title")
        assertThat(albumUiModel.thumbnailUrl).isEqualTo("thumbnail")
    }

    @Test
    fun `toAlbumEntity - when given valid AlbumDto - should return correct AlbumEntity`() {
        // Arrange
        val albumDto = AlbumDto(
            id = 1,
            albumId = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbnail"
        )

        // Act
        val albumEntity = albumDto.toAlbumEntity()

        // Assert
        assertThat(albumEntity.id).isEqualTo(1)
        assertThat(albumEntity.albumId).isEqualTo(1)
        assertThat(albumEntity.title).isEqualTo("title")
        assertThat(albumEntity.url).isEqualTo("url")
        assertThat(albumEntity.thumbnailUrl).isEqualTo("thumbnail")
    }

}