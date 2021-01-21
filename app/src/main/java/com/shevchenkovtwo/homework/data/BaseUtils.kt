package com.shevchenkovtwo.homework.data

import com.shevchenkovtwo.homework.data.models.Actor
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.network.api.RetrofitInstance

const val BACKDROP = "w342"
const val POSTER = "w185"


suspend fun setMovieDetails(movie: MovieDetails): MovieDetails {
    return MovieDetails(
        tmdbId = movie.tmdbId,
        title = movie.title,
        overview = movie.overview,
        poster = setImageURL(POSTER, movie.poster),
        backdrop = setImageURL(BACKDROP, movie.backdrop),
        ratings = movie.ratings,
        numberOfRatings = movie.numberOfRatings,
        age = movie.age,
        runtime = movie.runtime,
        genres = movie.genres
    )
}

suspend fun setActors(actor: Actor): Actor {
    return Actor(
        id = actor.id,
        name = actor.name,
        picture = setImageURL(POSTER, actor.picture)
    )
}

suspend fun setImageURL(size: String, imageUrl: String? = ""): String {
    var url = ""
    RetrofitInstance.api.getConfiguration().body()?.apply {
        url = "${configuration.imagesSecureUrl}$size$imageUrl"
    }
    return url
}
