package com.nunopeixoto.leboncointest.utils.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp interceptor that adds a "User-Agent" header to outgoing HTTP requests.
 * This class is used to modify requests by including a custom User-Agent string.
 *
 * @param userAgent The User-Agent string to be added to the request headers.
 */
class UserAgentInterceptor(private val userAgent: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request from the chain.
        val originalRequest = chain.request()

        // Create a new request with the "User-Agent" header added.
        val requestWithUserAgent = originalRequest.newBuilder()
            .header("User-Agent", userAgent)
            .build()

        // Proceed with the modified request and return the response.
        return chain.proceed(requestWithUserAgent)
    }
}