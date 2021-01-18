package com.shevchenkovtwo.homework.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class MovieDetails(
    @Json(name = "id")
    val tmdbId: Int,
    val title: String,
    val overview: String?,
    @Json(name = "poster_path")
    val poster: String?,
    @Json(name = "backdrop_path")
    val backdrop: String?,
    @Json(name = "vote_average")
    val ratings: Float,
    @Json(name = "vote_count")
    val numberOfRatings: Int,
    @Json(name = "adult")
    val age: Boolean,
    val runtime: Int?,
    val genres: List<Genre>
) : Parcelable