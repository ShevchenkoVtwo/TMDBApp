package com.shevchenkovtwo.homework.network.datasources

import com.shevchenkovtwo.homework.di.IODispatcher
import com.shevchenkovtwo.homework.network.api.TMDBApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesNetworkDataSource @Inject constructor(
    private val api: TMDBApi,
    @IODispatcher private val dispatcherIO: CoroutineDispatcher
) : BaseDataSource() {

    suspend fun getConfiguration() = getResult { withContext(dispatcherIO) { api.getConfiguration() } }

    suspend fun getActors(id: Int) = getResult { withContext(dispatcherIO) { api.getActors(id) } }

    suspend fun getMovieDetails(id: Int) = getResult { withContext(dispatcherIO) { api.getMovieDetails(id) } }

    suspend fun getNowPlayingMovies(page: Int) = getResult { withContext(dispatcherIO) { api.getNowPlayingMovies(page = page) } }

    suspend fun getPopularMovies(page: Int) = getResult { withContext(dispatcherIO) { api.getPopularMovies(page = page) } } // TODO add ui to display

    suspend fun getTopRatedMovies(page: Int) = getResult { withContext(dispatcherIO) { api.getTopRatedMovies(page = page) } } // TODO add ui to display

    suspend fun getUpComingMovies(page: Int) = getResult { withContext(dispatcherIO) { api.getUpComingMovies(page = page) } } // TODO add ui to display

}