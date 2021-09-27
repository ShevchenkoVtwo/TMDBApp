package com.shevchenkovtwo.tmdbApp.data.usecases

import androidx.paging.PagingData
import com.shevchenkovtwo.tmdbApp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface LoadMovieUseCase {
    suspend fun loadMovie(): Flow<PagingData<Movie>>
}
