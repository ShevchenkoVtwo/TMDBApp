package com.shevchenkovtwo.tmdbApp.data.repositoryImpl

import com.shevchenkovtwo.tmdbApp.data.utils.mapToDomainActorsListWithUrl
import com.shevchenkovtwo.tmdbApp.domain.models.Actor
import com.shevchenkovtwo.tmdbApp.domain.models.Configuration
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.tmdbApp.domain.network.utils.DataState
import com.shevchenkovtwo.tmdbApp.domain.repository.ActorsRepository
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : ActorsRepository {

    override suspend fun loadActors(id: Int, configuration: Configuration): List<Actor> {
        val response = dataSource.getActors(id)
        var actors: List<Actor> = emptyList()
        when (response) {
            is DataState.Success -> actors =
                mapToDomainActorsListWithUrl(response.data.actors, configuration)
            is DataState.Error -> throw error(response.exception)
            else -> DataState.Loading
        }
        return actors
    }

}