package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.network.models.MovieDto
import com.squareup.moshi.Json


data class MoviesResponse(
    @Json(name = "results")
    val movies: List<MovieDto>
)