package com.nunopeixoto.leboncointest.presentation.state

/**
 * A sealed class representing the possible states of the UI.
 * This class is used to model the different states that the UI can be in.
 * Sealed classes ensure type safety by restricting the possible subclasses to a known set.
 */
sealed class UiState {

    /**
     * Represents the loading state of the UI.
     * This state is used when data is being fetched or processed, and the UI should display a loading indicator.
     */
    data object Loading: UiState()

    /**
     * Represents the state where data is ready to be displayed in the UI.
     * This state is used when the data has been successfully loaded and the UI should render it.
     */
    data object ShowData: UiState()

    /**
     * Represents the state where there is no data to display in the UI.
     * This state is used when there is no data available to be displayed.
     */
    data object NoData: UiState()
}
