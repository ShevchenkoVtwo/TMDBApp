package com.shevchenkovtwo.homework.repositoryImpl

import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.homework.network.models.MovieDetailsDto
import com.shevchenkovtwo.homework.network.utils.DataState
import com.shevchenkovtwo.homework.network.utils.mapToDetailsDtoWithUrl
import com.shevchenkovtwo.homework.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : MovieDetailsRepository {

    override suspend fun loadDetails(id: Int, configuration: Configuration): MovieDetailsDto {
        val response = dataSource.getMovieDetails(id)
        lateinit var data: MovieDetailsDto
        when (response) {
            is DataState.Success -> data = mapToDetailsDtoWithUrl(response.data, configuration)
            is DataState.Error -> throw response.exception
            else -> DataState.Loading
        }
        return data
    }

}