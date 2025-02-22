package com.nunopeixoto.leboncointest.utils.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * A custom qualifier annotation used to distinguish the IO dispatcher dependency.
 * This annotation is used in conjunction with Hilt to provide a specific [CoroutineDispatcher]
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

/**
 * Provides an instance of [CoroutineDispatcher] configured for IO-bound operations.
 * This method is annotated with [@Singleton] to ensure that a single instance of the dispatcher
 * is shared across the application.
 *
 * @return An instance of [CoroutineDispatcher] configured with [Dispatchers.IO].
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    @Singleton
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}