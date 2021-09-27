package com.shevchenkovtwo.tmdbApp.domain.network.responses

import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.MovieDto
import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "results")
    val movies: List<MovieDto>
)
