package com.shevchenkovtwo.homework.ui.viewmodles

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.data.loadMovies
import com.shevchenkovtwo.homework.ui.adapters.MoviesAdapter.Companion.selectedMovie
import kotlinx.coroutines.launch


class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    //TODO rework to ViewModel when will be added Retrofit
    private val mutableMovieListLiveData = MutableLiveData<List<Movie>>(emptyList())
    private val mutableMovieLiveData = MutableLiveData<Movie>()
    val moviesList: MutableLiveData<List<Movie>> get() = mutableMovieListLiveData
    val movie: MutableLiveData<Movie> get() = mutableMovieLiveData

    fun loadMoviesData() {
        if (moviesList.value.isNullOrEmpty()) {
            viewModelScope.launch {
                moviesList.postValue(loadMovies(getApplication()))
            }
        }
    }

    fun loadSelectedMovie(bundle: Bundle) {
        movie.value = bundle.getParcelable(selectedMovie)
    }
}