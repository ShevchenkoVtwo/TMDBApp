package com.shevchenkovtwo.homework.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.data.MoviesRepository
import kotlinx.coroutines.flow.Flow


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    private var currentMoviesResult: Flow<PagingData<MovieDetails>>? = null

    fun loadNowPlayingMovies(): Flow<PagingData<MovieDetails>> {
        val lastResult = currentMoviesResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult = repository.getNowPlayingMovies().cachedIn(viewModelScope)
        currentMoviesResult = newResult
        return newResult
    }
}