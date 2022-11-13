package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.data.repository.MainRepository
import com.podium.technicalchallenge.data.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}