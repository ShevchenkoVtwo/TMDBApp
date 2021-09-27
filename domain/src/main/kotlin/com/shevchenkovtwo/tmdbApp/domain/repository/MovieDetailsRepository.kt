package com.shevchenkovtwo.tmdbApp.domain.repository

import com.shevchenkovtwo.tmdbApp.domain.models.Configuration
import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDetailsDto

interface MovieDetailsRepository {

    suspend fun loadDetails(id: Int, configuration: Configuration): MovieDetailsDto

}
