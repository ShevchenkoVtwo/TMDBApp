package com.shevchenkovtwo.homework.network.models

import com.shevchenkovtwo.homework.basemodels.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieDetailsDto(
    @Json(name = "backdrop_path")
    val backdrop: String?,
    val runtime: Int?,
    val genres: List<Genre>
)