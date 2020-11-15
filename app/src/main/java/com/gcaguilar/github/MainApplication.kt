package com.gcaguilar.github

import android.app.Application
import com.gcaguilar.github.di.dataModule
import com.gcaguilar.github.di.domainModule
import com.gcaguilar.github.di.mainModule
import com.gcaguilar.github.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, dataModule, domainModule, mainModule)
        }
    }
}