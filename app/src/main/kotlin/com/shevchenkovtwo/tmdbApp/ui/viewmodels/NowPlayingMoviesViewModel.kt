package com.shevchenkovtwo.tmdbApp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shevchenkovtwo.tmdbApp.domain.models.Movie
import com.shevchenkovtwo.tmdbApp.data.usecases.LoadMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NowPlayingMoviesViewModel @Inject constructor(
    private val useCase: LoadMovieUseCase
) : ViewModel() {

    private var _nowPlayingMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val nowPlayingMovies: StateFlow<PagingData<Movie>> = _nowPlayingMovies.asStateFlow()

    init {
        loadNowPlayingMovies()
    }

    @ExperimentalCoroutinesApi
    fun loadNowPlayingMovies() {
        viewModelScope.launch {
            useCase.loadMovie().cachedIn(viewModelScope).collectLatest { data ->
                _nowPlayingMovies.value = data
            }
        }
    }

}
