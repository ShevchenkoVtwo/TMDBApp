package com.shevchenkovtwo.homework.di

import com.shevchenkovtwo.homework.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.homework.network.datasources.NowPlayingMoviesPagingSource
import com.shevchenkovtwo.homework.repository.ActorsRepository
import com.shevchenkovtwo.homework.repository.ConfigurationRepository
import com.shevchenkovtwo.homework.repository.MovieDetailsRepository
import com.shevchenkovtwo.homework.repository.MoviesRepository
import com.shevchenkovtwo.homework.repositoryImpl.ActorsRepositoryImpl
import com.shevchenkovtwo.homework.repositoryImpl.ConfigurationRepositoryImpl
import com.shevchenkovtwo.homework.repositoryImpl.MovieDetailsRepositoryImpl
import com.shevchenkovtwo.homework.repositoryImpl.NowPlayingMoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(pagingSource: NowPlayingMoviesPagingSource): MoviesRepository =
        NowPlayingMoviesRepositoryImpl(pagingSource)

    @Provides
    @Singleton
    fun provideActorsRepository(dataSource: MoviesNetworkDataSource): ActorsRepository =
        ActorsRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(dataSource: MoviesNetworkDataSource): MovieDetailsRepository =
        MovieDetailsRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideConfigurationRepository(dataSource: MoviesNetworkDataSource): ConfigurationRepository =
        ConfigurationRepositoryImpl(dataSource)

}