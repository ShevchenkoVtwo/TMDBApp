package com.shevchenkovtwo.tmdbApp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shevchenkovtwo.tmdbApp.ui.viewmodels.NowPlayingMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingMoviesViewModel::class)
    internal abstract fun bindNowPlayingMoviesViewModel(viewModel: NowPlayingMoviesViewModel): ViewModel

}
