package com.shevchenkovtwo.homework.network.utils

import com.shevchenkovtwo.homework.network.models.ActorDto
import com.shevchenkovtwo.homework.network.models.ConfigurationDto
import com.shevchenkovtwo.homework.network.models.MovieDetailsDto
import com.shevchenkovtwo.homework.network.models.MovieDto
import com.shevchenkovtwo.homework.basemodels.Actor
import com.shevchenkovtwo.homework.basemodels.Configuration
import com.shevchenkovtwo.homework.basemodels.Movie


enum class ImageSizes(val size: String) {
    SMALL("w92"), MEDIUM("w185"), BIG("w342")
}

private fun mapImageUrl(baseUrl: String, size: String, imageUrl: String) =
    "$baseUrl$size$imageUrl"

private fun calculateMovieRatingToDomain(rating: Float): Float = rating / 2

private fun mapToDomainActorModelWithUrl(model: ActorDto, configuration: Configuration) = Actor(
    id = model.id,
    name = model.name,
    picture = model.picture?.let { mapImageUrl(configuration.imageUrl, ImageSizes.SMALL.size, it) }
)

fun mapToMovieDtoWithImageUrl(model: MovieDto, configuration: Configuration) = MovieDto(
    tmdbId = model.tmdbId,
    name = model.name,
    overview = model.overview,
    poster = model.poster?.let { poster -> mapImageUrl(configuration.imageUrl, ImageSizes.MEDIUM.size, poster) },
    ratings = calculateMovieRatingToDomain(model.ratings),
    numberOfRatings = model.numberOfRatings,
    age = model.age,
)

fun mapToDomainConfigurationModel(model: ConfigurationDto) = Configuration(
    imageUrl = model.imagesSecureUrl
)

fun mapToDomainActorsListWithUrl(initial: List<ActorDto>, configuration: Configuration) =
    initial.map { mapToDomainActorModelWithUrl(it, configuration) }

fun mapToDetailsDtoWithUrl(model: MovieDetailsDto, configuration: Configuration) = MovieDetailsDto(
    backdrop = model.backdrop?.let { backdrop -> mapImageUrl(configuration.imageUrl, ImageSizes.BIG.size, backdrop) },
    runtime = model.runtime,
    genres = model.genres,
)

fun mapToDomainMovieModel(model: MovieDto, details: MovieDetailsDto, actors: List<Actor>) = Movie(
    id = model.tmdbId,
    name = model.name,
    overview = model.overview,
    poster = model.poster,
    backdrop = details.backdrop,
    ratings = model.ratings,
    numberOfRatings = model.numberOfRatings,
    age = if (model.age) 16 else 13,
    runtime = details.runtime,
    genres = details.genres,
    actors = actors
)


