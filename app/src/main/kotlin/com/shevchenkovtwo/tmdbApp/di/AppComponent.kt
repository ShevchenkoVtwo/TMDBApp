package com.shevchenkovtwo.tmdbApp.di

import android.content.Context
import com.shevchenkovtwo.tmdbApp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun application(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}
