package com.shevchenkovtwo.homework.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shevchenkovtwo.homework.data.models.Actor
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.data.paginsources.NowPlayingMoviesPagingSource
import com.shevchenkovtwo.homework.network.ResponsesLogging
import com.shevchenkovtwo.homework.network.api.RetrofitInstance
import com.shevchenkovtwo.homework.network.api.TMDBApi
import com.shevchenkovtwo.homework.network.responses.ActorResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class MoviesRepository(private val api: TMDBApi = RetrofitInstance.api) {

    fun getNowPlayingMovies(): Flow<PagingData<MovieDetails>> {
        return Pager(config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { NowPlayingMoviesPagingSource(api) }).flow
    }

    suspend fun getActorsWithId(id: Int): ResponsesLogging<List<Actor>> {
        val actors = mutableListOf<Actor>()
        api.getActors(id).body()?.actors?.forEach { actor ->
            actors.add(setActors(actor))
        }
        return if (actors.isNullOrEmpty()) ResponsesLogging.Error("Something went wrong")
        else ResponsesLogging.Success(actors)
    }

    private fun handleResponse(response: Response<ActorResponse>): ResponsesLogging<ActorResponse> {
        if (response.isSuccessful) {
            response.body().let {
                return ResponsesLogging.Success(it)
            }
        }
        return ResponsesLogging.Error(response.message())
    }

    private suspend fun setActors(actor: Actor): Actor {
        return Actor(
            id = actor.id,
            name = actor.name,
            picture = setImageURL(POSTER, actor.picture)
        )
    }

    companion object {
        private const val PAGE_SIZE = 10
        const val BACKDROP = "w342"
        const val POSTER = "w185"
        suspend fun setImageURL(size: String, imageUrl: String? = ""): String {
            var url = ""
            RetrofitInstance.api.getConfiguration().body()?.apply {
                url = "${configuration.imagesSecureUrl}$size$imageUrl"
            }
            return url
        }
    }
}
