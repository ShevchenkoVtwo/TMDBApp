package com.shevchenkovtwo.tmdbApp.domain.network.responses

import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.ActorDto
import com.squareup.moshi.Json

data class ActorResponse(
    @Json(name = "cast")
    val actors: List<ActorDto>
)
