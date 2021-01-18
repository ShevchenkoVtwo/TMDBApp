package com.shevchenkovtwo.homework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shevchenkovtwo.homework.data.MoviesRepository
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesViewModel::class.java -> MoviesViewModel(MoviesRepository())
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(MoviesRepository())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}