package com.nunopeixoto.leboncointest.presentation.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.nunopeixoto.leboncointest.MainDispatcherRule
import com.nunopeixoto.leboncointest.data.remote.response.AlbumDto
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.domain.usecase.FetchAlbumsFromNetworkUseCase
import com.nunopeixoto.leboncointest.domain.usecase.GetAlbumsUseCase
import com.nunopeixoto.leboncointest.domain.usecase.SaveAlbumsUseCase
import com.nunopeixoto.leboncointest.presentation.state.UiState
import com.nunopeixoto.leboncointest.utils.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAlbumsUseCase: GetAlbumsUseCase = mock()
    private val fetchAlbumsFromNetworkUseCase: FetchAlbumsFromNetworkUseCase = mock()
    private val saveAlbumsUseCase: SaveAlbumsUseCase = mock()
    private val pagingDataFlow = MutableSharedFlow<PagingData<AlbumUiModel>>()

    private lateinit var viewModel: AlbumsViewModel

    @Before
    fun setup() {
        whenever(getAlbumsUseCase()).thenReturn(pagingDataFlow)
        viewModel = AlbumsViewModel(
            getAlbumsUseCase,
            fetchAlbumsFromNetworkUseCase,
            saveAlbumsUseCase
        )
    }

    @Test
    fun `fetchData - when fetchAlbumsFromNetworkUseCase is success - should call saveAlbumsUseCase and update uiState`() =
        runTest {
            // Given
            val albumsDto = listOf(
                AlbumDto(1, 1, "title1", "url1", "thumb1"),
                AlbumDto(2, 2, "title2", "url2", "thumb2")
            )
            whenever(fetchAlbumsFromNetworkUseCase()).thenReturn(NetworkResult.Success(albumsDto))

            // When
            viewModel.uiState.test {
                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                viewModel.fetchData()
                assertThat(awaitItem()).isEqualTo(UiState.ShowData)
                verify(saveAlbumsUseCase).invoke(albumsDto)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `fetchData - when fetchAlbumsFromNetworkUseCase is error - should not call saveAlbumsUseCase and update uiState`() =
        runTest {
            // Given
            whenever(fetchAlbumsFromNetworkUseCase()).thenReturn(NetworkResult.Error("Unknown error"))

            // When
            viewModel.uiState.test {
                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                viewModel.fetchData()
                assertThat(awaitItem()).isEqualTo(UiState.ShowData)
                verify(saveAlbumsUseCase, never()).invoke(any())
                cancelAndIgnoreRemainingEvents()
            }
        }

}