package com.shevchenkovtwo.tmdbApp.data.repositoryImpl

import androidx.paging.*
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.NowPlayingMoviesPagingSource
import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDto
import com.shevchenkovtwo.tmdbApp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NowPlayingMoviesRepositoryImpl @Inject constructor(
    private val pagingSource: NowPlayingMoviesPagingSource,
) : MoviesRepository {

    override suspend fun loadMovies(): Flow<PagingData<MovieDto>> =
        Pager(config = config(),
            pagingSourceFactory = { pagingSource }).flow

    private fun config() = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = true
    )

    companion object {
        private const val PAGE_SIZE = 10
    }

}
