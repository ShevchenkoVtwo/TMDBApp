package com.shevchenkovtwo.homework.di

import com.shevchenkovtwo.homework.network.api.TMDBApi
import com.shevchenkovtwo.homework.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.homework.network.datasources.NowPlayingMoviesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMoviesNetworkDataSource(api: TMDBApi, @IODispatcher dispatcher: CoroutineDispatcher) = MoviesNetworkDataSource(api, dispatcher)

    @Provides
    @Singleton
    fun provideNowPlayingMoviesPagingSource(dataSource: MoviesNetworkDataSource) = NowPlayingMoviesPagingSource(dataSource)

}

