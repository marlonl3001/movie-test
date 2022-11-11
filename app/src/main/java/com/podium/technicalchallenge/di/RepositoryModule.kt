package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.data.repository.MoviesRepository
import com.podium.technicalchallenge.data.repository.MoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}