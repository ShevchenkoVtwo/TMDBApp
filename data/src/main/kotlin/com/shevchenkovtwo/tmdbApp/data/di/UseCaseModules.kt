package com.shevchenkovtwo.tmdbApp.data.di

import com.shevchenkovtwo.tmdbApp.data.usecases.GetNowPlayingMoviesWithActorsAndDetailsUseCase
import com.shevchenkovtwo.tmdbApp.domain.di.DefaultDispatcher
import com.shevchenkovtwo.tmdbApp.domain.repository.ActorsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.ConfigurationRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MovieDetailsRepository
import com.shevchenkovtwo.tmdbApp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
object UseCaseModules {

    @Provides
    @Singleton
    fun provideNowPlayingMoviesUseCase(
        configurationRepository: ConfigurationRepository,
        nowPlayingMoviesRepository: MoviesRepository,
        actorsRepository: ActorsRepository,
        movieDetailsRepository: MovieDetailsRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = GetNowPlayingMoviesWithActorsAndDetailsUseCase(
        configurationRepository,
        nowPlayingMoviesRepository,
        actorsRepository,
        movieDetailsRepository,
        defaultDispatcher
    )

}