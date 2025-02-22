package com.nunopeixoto.leboncointest.utils.network

/**
 * A sealed class representing the result of a network operation.
 * This class is used to encapsulate the success or failure of a network request,
 * providing a type-safe way to handle the result.
 *
 * @param T The type of data returned on a successful network request.
 */
sealed class NetworkResult<out T> {

    /**
     * Represents a successful network operation, containing the resulting data.
     *
     * @param data The data returned from the network request.
     */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /**
     * Represents a failed network operation, containing an error message.
     *
     * @param message A description of the error that occurred during the network request.
     */
    data class Error(val message: String) : NetworkResult<Nothing>()
}