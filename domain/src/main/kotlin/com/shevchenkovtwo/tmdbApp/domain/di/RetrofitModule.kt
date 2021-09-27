package com.shevchenkovtwo.tmdbApp.domain.di

import com.shevchenkovtwo.tmdbApp.domain.network.interceptors.AuthInterceptor
import com.shevchenkovtwo.tmdbApp.domain.network.api.TMDBApi
import com.shevchenkovtwo.tmdbApp.domain.network.utils.NetworkConfig.baseURL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideLogging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideTokenInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @Singleton
    fun provideClient(logging: HttpLoggingInterceptor, key: AuthInterceptor) = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(key)
        .build()

    @Provides
    @Singleton
    fun provideMovieService(moshi: Moshi, client: OkHttpClient): TMDBApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
        .create(TMDBApi::class.java)
}