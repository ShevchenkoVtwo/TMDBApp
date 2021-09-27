package com.shevchenkovtwo.tmdbApp.domain.repository

import com.shevchenkovtwo.tmdbApp.domain.models.Actor
import com.shevchenkovtwo.tmdbApp.domain.models.Configuration

interface ActorsRepository {

    suspend fun loadActors(id: Int,configuration: Configuration): List<Actor>

}
