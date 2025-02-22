package com.nunopeixoto.leboncointest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.nunopeixoto.leboncointest.MainDispatcherRule
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.domain.repository.AlbumLocalDatabaseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetAlbumsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val albumRepository: AlbumLocalDatabaseRepository = mock()
    private val getAlbumsUseCase = GetAlbumsUseCase(albumRepository)

    @Test
    fun `invoke - when repository returns albums - should return albums`() = runTest {
        // Given
        val albums = listOf(
            AlbumUiModel( id = 1,"title1", "thumb1"),
            AlbumUiModel( id = 2,"title2", "thumb2")
        )

        val pagingData = PagingData.from(albums)
        whenever(albumRepository.getAllAlbums()).thenReturn(flowOf(pagingData))

        // When
        val result: Flow<PagingData<AlbumUiModel>> = getAlbumsUseCase()
        val actualAlbums = result.asSnapshot()

        // Then
        assertThat(albums).isEqualTo(actualAlbums)
    }
}