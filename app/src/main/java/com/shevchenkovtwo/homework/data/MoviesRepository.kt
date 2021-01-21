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
import kotlinx.coroutines.flow.Flow


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

    companion object {
        private const val PAGE_SIZE = 10
    }
}
