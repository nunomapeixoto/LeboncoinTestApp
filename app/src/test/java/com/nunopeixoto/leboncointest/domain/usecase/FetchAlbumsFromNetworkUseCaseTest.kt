package com.nunopeixoto.leboncointest.domain.usecase

import com.nunopeixoto.leboncointest.MainDispatcherRule
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.repository.AlbumNetworkRepository
import com.nunopeixoto.leboncointest.utils.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FetchAlbumsFromNetworkUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val albumRepository: AlbumNetworkRepository = mock()
    private val fetchAlbumsFromNetworkUseCase = FetchAlbumsFromNetworkUseCase(albumRepository)

    @Test
    fun `invoke - when repository returns albums - should return success`() = runTest {
        // Given
        val albums = listOf(
            AlbumDto(1, 1, "title1", "url1", "thumb1"),
            AlbumDto(2, 2, "title2", "url2", "thumb2")
        )
        whenever(albumRepository.getAlbumsDto()).thenReturn(albums)

        // When
        val result = fetchAlbumsFromNetworkUseCase()

        // Then
        assertThat(result).isEqualTo(NetworkResult.Success(albums))
    }

    @Test
    fun `invoke - when repository throws exception - should return error`() = runTest {
        // Given
        val exception = RuntimeException("Network error")
        whenever(albumRepository.getAlbumsDto()).thenThrow(exception)

        // When
        val result = fetchAlbumsFromNetworkUseCase()

        // Then
        assertThat(result).isEqualTo(NetworkResult.Error("Unknown error"))
    }
}