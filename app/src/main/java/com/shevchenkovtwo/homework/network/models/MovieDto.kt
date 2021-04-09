package com.shevchenkovtwo.homework.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieDto (
    @Json(name = "id")
    val tmdbId: Int,
    @Json(name = "title")
    val name: String,
    val overview: String?,
    @Json(name = "poster_path")
    val poster: String?,
    @Json(name = "vote_average")
    val ratings: Float,
    @Json(name = "vote_count")
    val numberOfRatings: Int,
    @Json(name = "adult")
    val age: Boolean
)