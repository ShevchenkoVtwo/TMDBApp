package com.shevchenkovtwo.tmdbApp.data.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.shevchenkovtwo.tmdbApp.domain.models.Configuration
import com.shevchenkovtwo.tmdbApp.domain.models.Movie
import com.shevchenkovtwo.tmdbApp.data.utils.mapToDomainMovieModel
import com.shevchenkovtwo.tmdbApp.data.utils.mapToMovieDtoWithImageUrl
import com.shevchenkovtwo.tmdbApp.domain.di.DefaultDispatcher
import com.shevchenkovtwo.tmdbApp.domain.repository.ActorsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.ConfigurationRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MovieDetailsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MoviesRepository
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
) : LoadMovieUseCase {

    private suspend fun getConfiguration(): Configuration =
        configurationRepository.loadConfiguration()

    private suspend fun getActors(id: Int, configuration: Configuration) =
        actorsRepository.loadActors(id, configuration)

    private suspend fun getDetails(id: Int, configuration: Configuration) =
        movieDetailsRepository.loadDetails(id, configuration)

    @ExperimentalCoroutinesApi
    override suspend fun loadMovie(): Flow<PagingData<Movie>> {
        val configuration = getConfiguration()
        return nowPlayingMoviesRepository.loadMovies().mapLatest { data ->
            data.map { movie ->
                val movieDto = mapToMovieDtoWithImageUrl(movie, configuration)
                val actors =
                    withContext(defaultDispatcher) { getActors(movie.tmdbId, configuration) }
                val details =
                    withContext(defaultDispatcher) { getDetails(movie.tmdbId, configuration) }
                mapToDomainMovieModel(movieDto, details, actors)
            }
        }
    }
}
