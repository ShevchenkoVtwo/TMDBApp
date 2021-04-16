package com.shevchenkovtwo.homework.repositoryImpl

import com.shevchenkovtwo.homework.basemodels.Actor
import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.homework.network.utils.DataState
import com.shevchenkovtwo.homework.network.utils.mapToDomainActorsListWithUrl
import com.shevchenkovtwo.homework.repository.ActorsRepository
import javax.inject.Inject


class ActorsRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : ActorsRepository {

    override suspend fun loadActors(id: Int, configuration: Configuration): List<Actor> {
        val response = dataSource.getActors(id)
        var data: List<Actor> = emptyList()
        when (response) {
            is DataState.Success -> data = mapToDomainActorsListWithUrl(response.data.actors, configuration)
            is DataState.Error -> throw error(response.exception)
            else -> DataState.Loading
        }
        return data
    }

}