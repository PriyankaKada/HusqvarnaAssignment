package com.husqvarna.movies.di

import com.husqvarna.movies.data.api.TMDBApi
import com.husqvarna.movies.data.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun getApiRepository(tmdbApi: TMDBApi) = ApiRepository(tmdbApi)
}