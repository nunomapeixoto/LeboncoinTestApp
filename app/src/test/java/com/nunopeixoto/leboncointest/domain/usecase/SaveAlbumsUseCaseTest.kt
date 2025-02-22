package com.nunopeixoto.leboncointest.domain.usecase

import com.nunopeixoto.leboncointest.MainDispatcherRule
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SaveAlbumsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val albumRepository: AlbumLocalDatabaseRepository = mock()
    private val saveAlbumsUseCase = SaveAlbumsUseCase(albumRepository)

    @Test
    fun `invoke - when called - should call saveAlbums on repository`() = runTest {
        // Given
        val albums = listOf(
            AlbumDto(1, 1, "title1", "url1", "thumb1"),
            AlbumDto(2, 2, "title2", "url2", "thumb2")
        )

        // When
        saveAlbumsUseCase(albums)

        // Then
        verify(albumRepository).saveAlbums(albums)
    }
}