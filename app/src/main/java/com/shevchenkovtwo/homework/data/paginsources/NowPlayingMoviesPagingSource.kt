package com.shevchenkovtwo.homework.data.paginsources

import androidx.paging.PagingSource
import com.shevchenkovtwo.homework.data.MoviesRepository.Companion.BACKDROP
import com.shevchenkovtwo.homework.data.MoviesRepository.Companion.POSTER
import com.shevchenkovtwo.homework.data.MoviesRepository.Companion.setImageURL
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.network.api.TMDBApi
import retrofit2.HttpException
import java.io.IOException


class NowPlayingMoviesPagingSource(private val network: TMDBApi) :
    PagingSource<Int, MovieDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        try {
            val currentLoadingPage = params.key ?: 1
            val prevPage = if (currentLoadingPage == 1) null else currentLoadingPage - 1
            val nextPage = currentLoadingPage.plus(1)
            val responseMoviesIds =
                network.getNowPlayingMoviesId(page = currentLoadingPage)

            val responseDetails = mutableListOf<MovieDetails>()

            responseMoviesIds.body()?.moviesId?.forEach { id ->
                network.getMovieWithId(id.tmdbId).body()
                    ?.let { responseDetails.add(setMovieDetails(it)) }
            }

            return LoadResult.Page(
                data = responseDetails,
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private suspend fun setMovieDetails(movie: MovieDetails): MovieDetails {
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
}
