package com.shevchenkovtwo.homework.ui.viewmodles

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.data.loadMovies
import com.shevchenkovtwo.homework.ui.adapters.MoviesAdapter.Companion.selectedMovie
import kotlinx.coroutines.launch


class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    //TODO rework to ViewModel when will be added Retrofit
    private val mutableMovieListLiveData = MutableLiveData<List<Movie>>(emptyList())
    private val mutableMovieLiveData = MutableLiveData<Movie>()
    val moviesList: LiveData<List<Movie>> get() = mutableMovieListLiveData
    val movie: LiveData<Movie> get() = mutableMovieLiveData

    fun loadMoviesData() {
        if (mutableMovieListLiveData.value.isNullOrEmpty()) {
            viewModelScope.launch {
                mutableMovieListLiveData.postValue(loadMovies(getApplication()))
            }
        }
    }

    fun loadSelectedMovie(bundle: Bundle) {
        mutableMovieLiveData.value = bundle.getParcelable(selectedMovie)
    }
}