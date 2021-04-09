package com.shevchenkovtwo.homework.repositoryImpl

import androidx.paging.*
import com.shevchenkovtwo.homework.network.datasources.NowPlayingMoviesPagingSource
import com.shevchenkovtwo.homework.network.models.MovieDto
import com.shevchenkovtwo.homework.repository.MoviesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class NowPlayingMoviesRepositoryImpl @Inject constructor(
    private val pagingSource: NowPlayingMoviesPagingSource,
) : MoviesRepository {

    override suspend fun loadMovies(): Flow<PagingData<MovieDto>> =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { pagingSource }).flow

    companion object {
        private const val PAGE_SIZE = 10
    }

}