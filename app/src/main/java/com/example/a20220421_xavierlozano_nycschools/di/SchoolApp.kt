package com.example.a20220421_xavierlozano_nycschools.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SchoolApp : Application() {

    /**
     * starts Koin [startKoin]
     */

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SchoolApp)
            modules(listOf(NetworkModule, viewModelModule))
        }
    }
}