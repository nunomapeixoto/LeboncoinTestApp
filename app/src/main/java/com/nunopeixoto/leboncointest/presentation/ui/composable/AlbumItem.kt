package com.nunopeixoto.leboncointest.presentation.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nunopeixoto.leboncointest.domain.model.AlbumUiModel

/**
 * A composable function that displays an individual album item in the UI.
 * This function shows the album's title and a thumbnail image.
 *
 * @param albumUiModel The [AlbumUiModel] object containing the album's data to be displayed.
 */
@Composable
fun AlbumItem(albumUiModel: AlbumUiModel) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Title: ${albumUiModel.title}")
        // Display the album's thumbnail image using Coil's AsyncImage.
        AsyncImage(
            //Since the placeholder repo from the json file is not working
            model = "https://placehold.co/100x100/png",//albumUiModel.thumbnailUrl,
            contentDescription = "Album Cover Thumbnail",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(
    name = "AlbumItem",
    showBackground = true,
    widthDp = 200,
    heightDp = 200
)
@Composable
fun AlbumItemDarkPreview() {
    AlbumItem(albumUiModel = AlbumUiModel(id = 1, title = "Album", thumbnailUrl = ""))
}