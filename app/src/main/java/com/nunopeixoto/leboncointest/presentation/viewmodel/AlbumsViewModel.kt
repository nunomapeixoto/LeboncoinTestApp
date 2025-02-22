package com.nunopeixoto.leboncointest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.domain.usecase.FetchAlbumsFromNetworkUseCase
import com.nunopeixoto.leboncointest.domain.usecase.GetAlbumsUseCase
import com.nunopeixoto.leboncointest.domain.usecase.SaveAlbumsUseCase
import com.nunopeixoto.leboncointest.presentation.state.UiState
import com.nunopeixoto.leboncointest.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing album data and UI state for the application.
 * This class integrates use cases to fetch, save, and retrieve albums, and provides
 * paginated data for the UI. It also manages the UI state.
 *
 * @param getAlbumsUseCase The use case for retrieving albums from the local database.
 * @param fetchAlbumsFromNetworkUseCase The use case for fetching albums from the network.
 * @param saveAlbumsUseCase The use case for saving albums to the local database.
 */
@HiltViewModel
class AlbumsViewModel @Inject constructor(
    getAlbumsUseCase: GetAlbumsUseCase,
    private val fetchAlbumsFromNetworkUseCase: FetchAlbumsFromNetworkUseCase,
    private val saveAlbumsUseCase: SaveAlbumsUseCase
) : ViewModel() {

    /**
     * A [Flow] of paginated album data.
     * The data is cached in the [viewModelScope] to survive configuration changes.
     */
    val albums: Flow<PagingData<AlbumUiModel>> = getAlbumsUseCase().cachedIn(viewModelScope)

    /**
     * Internal mutable state for tracking the current UI state.
     * This is exposed as a read-only [StateFlow] to the UI.
     */
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    /**
     * Fetches album data from the network and updates the local database.
     * This function handles the network request,
     * on success saves the fetched data to the local database,
     * And finally updates the UI state.
     */
    fun fetchData() {
        viewModelScope.launch {
            when (val result = fetchAlbumsFromNetworkUseCase()) {
                is NetworkResult.Error -> {
                    //This could be used to inform the user that it was not possible to fetch the
                    //data and we were working with offline data
                }

                is NetworkResult.Success -> saveAlbumsUseCase(result.data)
            }
            _uiState.update {
                UiState.ShowData
            }
        }
    }
}