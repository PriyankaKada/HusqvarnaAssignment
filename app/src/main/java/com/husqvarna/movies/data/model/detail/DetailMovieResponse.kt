package com.husqvarna.movies.data.model.detail

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double
) {
    fun getMovieBanner() = "https://image.tmdb.org/t/p/original/$backdropPath"
}