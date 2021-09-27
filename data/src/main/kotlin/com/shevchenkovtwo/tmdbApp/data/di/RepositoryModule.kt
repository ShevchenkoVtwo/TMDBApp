package com.shevchenkovtwo.tmdbApp.data.di

import com.shevchenkovtwo.tmdbApp.domain.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.NowPlayingMoviesPagingSource
import com.shevchenkovtwo.tmdbApp.domain.repository.ActorsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.ConfigurationRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MovieDetailsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MoviesRepository
import com.shevchenkovtwo.tmdbApp.data.repositoryImpl.ActorsRepositoryImpl
import com.shevchenkovtwo.tmdbApp.data.repositoryImpl.ConfigurationRepositoryImpl
import com.shevchenkovtwo.tmdbApp.data.repositoryImpl.MovieDetailsRepositoryImpl
import com.shevchenkovtwo.tmdbApp.data.repositoryImpl.NowPlayingMoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
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
