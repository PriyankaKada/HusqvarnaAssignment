package com.husqvarna.movies.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.movies.data.model.detail.DetailMovieResponse
import com.husqvarna.movies.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {
    private val movieDetailLiveData = MutableLiveData<DetailMovieResponse>()
    val movieDetail: LiveData<DetailMovieResponse> = movieDetailLiveData

    fun getMoviesDetail(movie_id: String) {
        viewModelScope.launch {
            val movieDetailList = apiRepository.getMoviesDetail(movie_id)
             movieDetailLiveData.value = movieDetailList
        }
    }
}