package com.nunopeixoto.leboncointest.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.asSnapshot
import com.nunopeixoto.leboncointest.data.local.dao.AlbumDao
import com.nunopeixoto.leboncointest.data.local.entity.AlbumEntity
import com.nunopeixoto.leboncointest.data.mapper.toAlbumEntity
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class AlbumLocalDatabaseRepositoryImplTest {

    private lateinit var albumDao: AlbumDao
    private lateinit var repository: AlbumLocalDatabaseRepositoryImpl

    @Before
    fun setUp() {
        albumDao = mock()
        repository = AlbumLocalDatabaseRepositoryImpl(albumDao)
    }

    @Test
    fun `saveAlbums should map DTOs to entities and call insertAll`() = runTest {
        // Arrange
        val albumsDto = listOf(
            AlbumDto(id = 1, albumId = 1, title = "Album 1", url = "url1", thumbnailUrl = "thumb1"),
            AlbumDto(id = 2, albumId = 2, title = "Album 2", url = "url2", thumbnailUrl = "thumb2"),
        )
        val albumEntities = albumsDto.map { it.toAlbumEntity() }

        // Act
        repository.saveAlbums(albumsDto)

        // Assert
        verify(albumDao).clearAndInsertAll(albumEntities)
    }

    @Test
    fun `getAllAlbums - should return paging data mapped to UiModel`() = runTest {
        // Arrange
        val albumEntities = listOf(
            AlbumEntity(
                id = 1,
                albumId = 1,
                title = "Album 1",
                url = "url1",
                thumbnailUrl = "thumb1"
            ),
            AlbumEntity(
                id = 2,
                albumId = 2,
                title = "Album 2",
                url = "url2",
                thumbnailUrl = "thumb2"
            ),
        )

        val pagingSource = object : PagingSource<Int, AlbumEntity>() {
            override fun getRefreshKey(state: PagingState<Int, AlbumEntity>): Int? =
                null

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumEntity> {
                return LoadResult.Page(
                    data = albumEntities,
                    prevKey = null,
                    nextKey = null
                )
            }
        }

        whenever(albumDao.getAllAlbumEntities()).thenReturn(pagingSource)

        // When
        val result: Flow<PagingData<AlbumUiModel>> = repository.getAllAlbums()
        val actualAlbums = result.asSnapshot()

        // Then
        assertThat(actualAlbums).isEqualTo(
            listOf(
                AlbumUiModel(
                    id = 1,
                    title = "Album 1",
                    thumbnailUrl = "thumb1"
                ),
                AlbumUiModel(
                    id = 2,
                    title = "Album 2",
                    thumbnailUrl = "thumb2"
                )
            )
        )
    }

    @Test
    fun `getAllAlbums - should return empty paging data when no albums exist`() = runTest {
        // Arrange
        val pagingSource = object : PagingSource<Int, AlbumEntity>() {
            override fun getRefreshKey(state: PagingState<Int, AlbumEntity>): Int? = null
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumEntity> {
                return LoadResult.Page(
                    data = emptyList(), // Empty list here
                    prevKey = null,
                    nextKey = null
                )
            }
        }
        whenever(albumDao.getAllAlbumEntities()).thenReturn(pagingSource)

        // When
        val result: Flow<PagingData<AlbumUiModel>> = repository.getAllAlbums()
        val actualAlbums = result.asSnapshot()

        // Then
        assertThat(actualAlbums).isEmpty()
    }
}
