package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.data.models.MoviesId
import com.squareup.moshi.Json


data class MoviesIdResponse(
    @Json(name = "results")
    val moviesId: List<MoviesId>
)