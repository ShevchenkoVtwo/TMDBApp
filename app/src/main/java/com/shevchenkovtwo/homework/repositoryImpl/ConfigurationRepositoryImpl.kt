package com.shevchenkovtwo.homework.repositoryImpl

import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.homework.network.utils.DataState
import com.shevchenkovtwo.homework.network.utils.mapToDomainConfigurationModel
import com.shevchenkovtwo.homework.repository.ConfigurationRepository
import javax.inject.Inject


class ConfigurationRepositoryImpl @Inject constructor(
    private val dataSource: MoviesNetworkDataSource
) : ConfigurationRepository {

    override suspend fun loadConfiguration(): Configuration {
        val response = dataSource.getConfiguration()
        lateinit var data: Configuration
        when (response) {
            is DataState.Success -> data = mapToDomainConfigurationModel(response.data.images)
            is DataState.Error -> throw response.exception
            else -> DataState.Loading
        }
        return data
    }
}
