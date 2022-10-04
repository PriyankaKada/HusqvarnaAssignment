package com.husqvarna.movies.data.repository

import com.husqvarna.movies.data.api.TMDBApi
import javax.inject.Inject

class ApiRepository @Inject
constructor(private val tmdbApi: TMDBApi) {
    suspend fun getPopularMovies() = tmdbApi.getPopularMovies()

    suspend fun getMoviesDetail(movie_id: String) = tmdbApi.getMoviesDetail(movie_id)
}