package com.shevchenkovtwo.tmdbApp.domain.network.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDto
import com.shevchenkovtwo.tmdbApp.domain.network.utils.DataState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NowPlayingMoviesPagingSource @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val currentLoadingPage = params.key ?: 1
            val prevPage = if (currentLoadingPage == 1) null else currentLoadingPage - 1
            val nextPage = currentLoadingPage.plus(1)
            val data = getMovie(currentLoadingPage)
            LoadResult.Page(
                data = data,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? = state.anchorPosition

    private suspend fun getMovie(page: Int): List<MovieDto> {
        val response = dataSource.getNowPlayingMovies(page)
        lateinit var movies: List<MovieDto>
        when (response) {
            is DataState.Success -> movies = response.data.movies
            is DataState.Error -> throw error(response.exception)
            else -> DataState.Loading
        }
        return movies
    }

}
