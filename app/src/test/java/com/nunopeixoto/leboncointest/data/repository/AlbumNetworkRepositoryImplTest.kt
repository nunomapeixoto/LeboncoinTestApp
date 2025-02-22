package com.nunopeixoto.leboncointest.data.repository

import com.nunopeixoto.leboncointest.data.remote.api.AlbumService
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.repository.AlbumNetworkRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AlbumNetworkRepositoryImplTest {

    private lateinit var repository: AlbumNetworkRepository
    private val albumService: AlbumService = mock()
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @Before
    fun setup() {
        repository = AlbumNetworkRepositoryImpl(albumService, testDispatcher)
    }

    @Test
    fun `getAlbumsDto - when api call is successful - should return list of AlbumDto`() = runTest(testScheduler) {
        // Given
        val albumsDto = listOf(
            AlbumDto(1, 1, "title1", "url1", "thumb1"),
            AlbumDto(2, 2, "title2", "url2", "thumb2")
        )
        whenever(albumService.getAlbums()).thenReturn(albumsDto)

        // When
        val result = repository.getAlbumsDto()

        // Then
        assertThat(result).isEqualTo(albumsDto)
    }

    @Test(expected = RuntimeException::class)
    fun `getAlbumsDto - when api call fails - should throw exception`() = runTest {
        // Given
        whenever(albumService.getAlbums()).thenThrow(RuntimeException("Network error"))

        // When
        repository.getAlbumsDto()

        // Then
        // Expecting a RuntimeException to be thrown
    }
}