package com.podium.technicalchallenge

import android.app.Application
import com.podium.technicalchallenge.di.moviesApiModule
import com.podium.technicalchallenge.di.networkModule
import com.podium.technicalchallenge.di.repositoryModule
import com.podium.technicalchallenge.di.useCaseModule
import com.podium.technicalchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ChallengeApp)
            modules(
                listOf(
                    moviesApiModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}