package com.shevchenkovtwo.homework.di

import com.shevchenkovtwo.homework.network.usecases.GetNowPlayingMoviesWithActorsAndDetailsUseCase
import com.shevchenkovtwo.homework.repository.ActorsRepository
import com.shevchenkovtwo.homework.repository.ConfigurationRepository
import com.shevchenkovtwo.homework.repository.MovieDetailsRepository
import com.shevchenkovtwo.homework.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
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