package com.shevchenkovtwo.homework.repository

import com.shevchenkovtwo.homework.basemodels.Actor
import com.shevchenkovtwo.homework.basemodels.Configuration


interface ActorsRepository {

    suspend fun loadActors(id: Int,configuration: Configuration): List<Actor>

}