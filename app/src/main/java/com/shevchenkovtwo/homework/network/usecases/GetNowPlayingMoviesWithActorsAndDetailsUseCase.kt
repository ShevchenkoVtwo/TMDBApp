package com.shevchenkovtwo.homework.network.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.basemodels.Movie
import com.shevchenkovtwo.homework.di.DefaultDispatcher
import com.shevchenkovtwo.homework.network.utils.mapToDomainMovieModel
import com.shevchenkovtwo.homework.network.utils.mapToMovieDtoWithImageUrl
import com.shevchenkovtwo.homework.repository.ActorsRepository
import com.shevchenkovtwo.homework.repository.ConfigurationRepository
import com.shevchenkovtwo.homework.repository.MovieDetailsRepository
import com.shevchenkovtwo.homework.repository.MoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetNowPlayingMoviesWithActorsAndDetailsUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
    private val nowPlayingMoviesRepository: MoviesRepository,
    private val actorsRepository: ActorsRepository,
    private val movieDetailsRepository: MovieDetailsRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    @ExperimentalCoroutinesApi
    suspend fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        val configuration = getConfiguration()
        return nowPlayingMoviesRepository.loadMovies().mapLatest { data ->
            data.map { movie ->
                val movieDto = mapToMovieDtoWithImageUrl(movie, configuration)
                val actors = withContext(defaultDispatcher) { getActors(movie.tmdbId, configuration) }
                val details = withContext(defaultDispatcher) { getDetails(movie.tmdbId, configuration) }
                mapToDomainMovieModel(movieDto, details, actors)
            }
        }
    }

    private suspend fun getConfiguration(): Configuration = configurationRepository.loadConfiguration()

    private suspend fun getActors(id: Int, configuration: Configuration) = actorsRepository.loadActors(id, configuration)

    private suspend fun getDetails(id: Int, configuration: Configuration) = movieDetailsRepository.loadDetails(id, configuration)
}