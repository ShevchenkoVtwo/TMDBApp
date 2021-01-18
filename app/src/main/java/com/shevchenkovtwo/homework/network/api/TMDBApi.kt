package com.shevchenkovtwo.homework.network.api

import com.shevchenkovtwo.homework.BuildConfig
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.network.responses.ActorResponse
import com.shevchenkovtwo.homework.network.responses.ConfigurationResponse
import com.shevchenkovtwo.homework.network.responses.MoviesIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesId(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<MoviesIdResponse>

    @GET("movie/popular")
    suspend fun getPopularMoviesId(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MoviesIdResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMoviesId(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MoviesIdResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMoviesId(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<MoviesIdResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieWithId(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetails>

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<ActorResponse>

    @GET("configuration")
    suspend fun getConfiguration(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<ConfigurationResponse>

}