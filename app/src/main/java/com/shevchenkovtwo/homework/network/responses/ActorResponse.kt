package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.data.models.Actor
import com.squareup.moshi.Json


data class ActorResponse(
    @Json(name = "cast")
    val actors: List<Actor>
)