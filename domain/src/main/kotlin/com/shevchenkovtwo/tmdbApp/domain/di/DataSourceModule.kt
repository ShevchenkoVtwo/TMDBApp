package com.shevchenkovtwo.tmdbApp.domain.di

import com.shevchenkovtwo.tmdbApp.domain.network.api.TMDBApi
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.MoviesNetworkDataSource
import com.shevchenkovtwo.tmdbApp.domain.network.datasources.NowPlayingMoviesPagingSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMoviesNetworkDataSource(api: TMDBApi, @IODispatcher dispatcher: CoroutineDispatcher) = MoviesNetworkDataSource(api, dispatcher)

    @Provides
    @Singleton
    fun provideNowPlayingMoviesPagingSource(dataSource: MoviesNetworkDataSource) = NowPlayingMoviesPagingSource(dataSource)

}
