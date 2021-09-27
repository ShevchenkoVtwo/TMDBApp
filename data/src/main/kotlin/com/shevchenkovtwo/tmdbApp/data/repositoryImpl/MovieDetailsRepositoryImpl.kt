package com.shevchenkovtwo.tmdbApp.data.repositoryImpl

import com.shevchenkovtwo.tmdbApp.domain.models.Configuration
import com.shevchenkovtwo.tmdbApp.data.utils.mapToDetailsDtoWithUrl
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDetailsDto
import com.shevchenkovtwo.tmdbApp.domain.network.utils.DataState
import com.shevchenkovtwo.tmdbApp.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : MovieDetailsRepository {

    override suspend fun loadDetails(id: Int, configuration: Configuration): MovieDetailsDto {
        val response = dataSource.getMovieDetails(id)
        lateinit var details: MovieDetailsDto
        when (response) {
            is DataState.Success -> details = mapToDetailsDtoWithUrl(response.data, configuration)
            is DataState.Error -> throw response.exception
            else -> DataState.Loading
        }
        return details
    }

}
