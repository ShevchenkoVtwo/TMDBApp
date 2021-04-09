package com.shevchenkovtwo.homework.repository

import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.network.models.MovieDetailsDto


interface MovieDetailsRepository {

    suspend fun loadDetails(id: Int, configuration: Configuration): MovieDetailsDto

}