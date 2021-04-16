package com.shevchenkovtwo.homework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.basemodels.Movie
import com.shevchenkovtwo.homework.network.usecases.GetNowPlayingMoviesWithActorsAndDetailsUseCase
import com.shevchenkovtwo.homework.network.utils.DataState
import com.shevchenkovtwo.homework.network.utils.mapToDomainConfigurationModel
import com.shevchenkovtwo.homework.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(private val useCase: GetNowPlayingMoviesWithActorsAndDetailsUseCase) : ViewModel() {

    private var _nowPlayingMovies: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val nowPlayingMovies: StateFlow<PagingData<Movie>> = _nowPlayingMovies.asStateFlow()

    init {
        loadNowPlayingMovies()
    }

    @ExperimentalCoroutinesApi
    fun loadNowPlayingMovies() {
        viewModelScope.launch {
            useCase.getNowPlayingMovies().cachedIn(viewModelScope).collectLatest { data ->
                _nowPlayingMovies.value = data
            }
        }
    }

}