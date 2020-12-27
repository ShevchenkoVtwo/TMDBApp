package com.shevchenkovtwo.homework


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shevchenkovtwo.homework.data.Movie

class MovieDetailViewModel : ViewModel() {

    private var movie: MutableLiveData<Movie>? = null

    init {
        movie = MutableLiveData()
    }

    fun getSelectedMovie(): MutableLiveData<Movie> {
        if (movie == null) {
            movie = MutableLiveData()
        }
        movie?.value = MoviesAdapter.selectedMovie
        return movie!!
    }
}