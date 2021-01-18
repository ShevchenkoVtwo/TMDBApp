package com.shevchenkovtwo.homework.data.models

import com.squareup.moshi.Json


data class MoviesId(
    @Json(name = "id")
    val tmdbId: Int)