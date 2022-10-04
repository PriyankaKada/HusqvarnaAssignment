package com.husqvarna.movies.data.api

import com.husqvarna.movies.BuildConfig
import com.husqvarna.movies.data.model.detail.DetailMovieResponse
import com.husqvarna.movies.data.model.popular.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): PopularMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): DetailMovieResponse
}