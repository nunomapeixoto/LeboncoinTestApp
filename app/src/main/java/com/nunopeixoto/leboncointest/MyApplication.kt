package com.nunopeixoto.leboncointest

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * The main application class for the project.
 * This class initializes dependency injection using Hilt and provides a custom [ImageLoader]
 * for loading images with Coil, using an injected [OkHttpClient].
 */
@HiltAndroidApp
class MyApplication : Application(), ImageLoaderFactory {

    /**
     * Injected instance of [OkHttpClient] provided by Hilt.
     * This client is used to configure the [ImageLoader] for Coil.
     */
    @Inject
    lateinit var okHttpClient: okhttp3.OkHttpClient

    /**
     * Creates and returns a new [ImageLoader] instance for Coil.
     * This method configures the [ImageLoader] with the injected [OkHttpClient] and a debug logger.
     *
     * @return A new [ImageLoader] instance configured with the provided [OkHttpClient].
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .okHttpClient(okHttpClient)
            .logger(DebugLogger())
            .build()
    }

}