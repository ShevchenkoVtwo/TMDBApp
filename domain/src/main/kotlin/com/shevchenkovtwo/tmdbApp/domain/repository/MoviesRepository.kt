package com.shevchenkovtwo.tmdbApp.domain.repository

import androidx.paging.PagingData
import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDto
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun loadMovies(): Flow<PagingData<MovieDto>>

}
