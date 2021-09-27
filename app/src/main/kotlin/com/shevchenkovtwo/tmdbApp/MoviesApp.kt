package com.shevchenkovtwo.tmdbApp

import android.app.Application
import com.shevchenkovtwo.tmdbApp.di.DaggerAppComponent

class MoviesApp : Application() {
    val appComponent by lazy { DaggerAppComponent.factory().application(this) }

    override fun onCreate() {
        super.onCreate()
    }

}
