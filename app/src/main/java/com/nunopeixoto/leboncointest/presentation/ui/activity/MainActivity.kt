package com.nunopeixoto.leboncointest.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import com.nunopeixoto.leboncointest.presentation.state.UiState
import com.nunopeixoto.leboncointest.presentation.ui.composable.AlbumList
import com.nunopeixoto.leboncointest.presentation.ui.composable.LoadingIndicator
import com.nunopeixoto.leboncointest.presentation.ui.theme.LeboncoinTestTheme
import com.nunopeixoto.leboncointest.presentation.viewmodel.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewModel.
        val viewModel: AlbumsViewModel by viewModels()

        setContent {
            LeboncoinTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Collect the paginated album data as LazyPagingItems for use in the UI.
                    val albums: LazyPagingItems<AlbumUiModel> =
                        viewModel.albums.collectAsLazyPagingItems()

                    // Collect the UI state from the ViewModel.
                    val uiState by viewModel.uiState.collectAsState()

                    // Display the appropriate UI based on the current state.
                    when (uiState) {
                        UiState.ShowData -> AlbumList(pagingItems = albums) { viewModel.fetchData() }
                        UiState.Loading -> LoadingIndicator()
                    }

                    // Trigger data fetching when the activity is launched.
                    LaunchedEffect(Unit) { viewModel.fetchData() }
                }
            }
        }
    }
}