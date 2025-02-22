package com.nunopeixoto.leboncointest.presentation.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nunopeixoto.leboncointest.R

/**
 * A composable function that displays an empty list state with a retry button.
 *
 * @param fetchData A lambda function that is triggered when the retry button is clicked.
 *                  This function should handle the logic for fetching data.
 */
@Composable
fun EmptyListWithRetryIndicator(fetchData: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.empty_list))
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = fetchData) { Text(stringResource(R.string.retry)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyListWithRetryIndicatorPreview() {
    EmptyListWithRetryIndicator(fetchData = {})
}