package com.shevchenkovtwo.homework.network.api

import com.shevchenkovtwo.homework.network.models.MovieDetailsDto
import com.shevchenkovtwo.homework.network.responses.ActorResponse
import com.shevchenkovtwo.homework.network.responses.ConfigurationResponse
import com.shevchenkovtwo.homework.network.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, ): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path("movie_id") movieId: Int, ): Response<ActorResponse>

    @GET("configuration")
    suspend fun getConfiguration(): Response<ConfigurationResponse>

}