package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.network.models.ActorDto
import com.squareup.moshi.Json


data class ActorResponse(
    @Json(name = "cast")
    val actors: List<ActorDto>
)