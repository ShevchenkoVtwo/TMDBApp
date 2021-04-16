package com.shevchenkovtwo.homework.repository

import androidx.paging.PagingData
import com.shevchenkovtwo.homework.network.models.MovieDto
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    suspend fun loadMovies(): Flow<PagingData<MovieDto>>

}