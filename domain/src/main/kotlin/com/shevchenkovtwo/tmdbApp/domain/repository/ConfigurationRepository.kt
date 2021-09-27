package com.shevchenkovtwo.tmdbApp.domain.repository

import com.shevchenkovtwo.tmdbApp.domain.models.Configuration

interface ConfigurationRepository {

    suspend fun loadConfiguration(): Configuration

}
