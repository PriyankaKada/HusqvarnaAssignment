package com.husqvarna.movies.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.movies.data.repository.ApiRepository
import com.husqvarna.movies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.husqvarna.movies.data.model.popular.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {


    private val resultLiveData = MutableLiveData<Resource<List<Result>>>()
    val result: LiveData<Resource<List<Result>>> = resultLiveData

    init {
        resultLiveData.value = Resource.Loading()
        viewModelScope.launch {
            val resultList = repository.getPopularMovies().results
            resultLiveData.value = Resource.Success(resultList)
        }
    }

}