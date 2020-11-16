package com.gcaguilar.github

import android.app.Application
import com.gcaguilar.github.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, databaseModule, dataModule, domainModule, mainModule)
        }
    }
}