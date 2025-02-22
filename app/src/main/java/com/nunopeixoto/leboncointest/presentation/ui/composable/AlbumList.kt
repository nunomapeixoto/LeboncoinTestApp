package com.nunopeixoto.leboncointest.presentation.ui.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel
import kotlinx.coroutines.flow.flowOf

/**
 * A composable function that displays a list of albums using paginated data.
 * This function uses [LazyColumn] to efficiently display a large list of albums
 * and handles loading and error states for pagination.
 *
 * @param pagingItems The [LazyPagingItems] containing the paginated album data.
 * @param fetchData The callback function to trigger data fetching when the [LazyPagingItems] is empty.
 */
@Composable
fun AlbumList(pagingItems: LazyPagingItems<AlbumUiModel>, fetchData: () -> Unit) {
    when {
        pagingItems.loadState.refresh is LoadState.Loading -> {
            LoadingIndicator()
        }

        pagingItems.loadState.refresh is LoadState.Error -> {
            Text(text = "Error")
        }

        pagingItems.itemCount == 0 && pagingItems.loadState.refresh is LoadState.NotLoading -> {
            EmptyListWithRetryIndicator(fetchData)
        }

        else -> {
            LazyColumn {
                items(
                    count = pagingItems.itemCount,
                    key = pagingItems.itemKey { it.id }
                ) { index ->
                    val album = pagingItems[index]
                    if (album != null) {
                        AlbumItem(albumUiModel = album)
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    }
                }

                // Handle loading and error states for pagination.
                pagingItems.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> {
                            item { LoadingIndicator() }
                        }

                        is LoadState.Error -> {
                            item { Text(text = "Error") }
                        }

                        is LoadState.NotLoading -> {
                            // Do nothing, the items are already displayed
                        }
                    }
                }
            }
        }
    }
}

fun generateSampleAlbums(count: Int): List<AlbumUiModel> {
    return (1..count).map { index ->
        AlbumUiModel(
            id = index,
            title = "Album Title $index",
            thumbnailUrl = "thumbnail"
        )
    }
}

@Composable
fun createDummyLazyPagingItems(albums: List<AlbumUiModel>): LazyPagingItems<AlbumUiModel> {
    val data = PagingData.from(albums)
    val flow = flowOf(data)
    return flow.collectAsLazyPagingItems()
}

@Preview(showBackground = true)
@Composable
fun AlbumListPreview() {
    val sampleAlbums = generateSampleAlbums(10)
    val pagingItems = createDummyLazyPagingItems(sampleAlbums)
    AlbumList(pagingItems = pagingItems) {}
}