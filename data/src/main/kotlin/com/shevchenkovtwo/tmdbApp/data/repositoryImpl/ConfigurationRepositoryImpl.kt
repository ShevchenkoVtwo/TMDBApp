package com.shevchenkovtwo.tmdbApp.data.repositoryImpl

import com.shevchenkovtwo.tmdbApp.data.utils.mapToDomainConfigurationModel
import com.shevchenkovtwo.tmdbApp.domain.models.Configuration
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.tmdbApp.domain.network.utils.DataState
import com.shevchenkovtwo.tmdbApp.domain.repository.ConfigurationRepository
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : ConfigurationRepository {

    override suspend fun loadConfiguration(): Configuration {
        val response = dataSource.getConfiguration()
        //TODO lateinit убрать
        lateinit var configuration: Configuration
        when (response) {
            is DataState.Success -> configuration = mapToDomainConfigurationModel(response.data.images)
            is DataState.Error -> throw response.exception
            else -> DataState.Loading
        }
        return configuration
    }

}
