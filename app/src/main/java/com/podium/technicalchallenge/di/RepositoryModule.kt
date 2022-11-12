package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.data.repository.HomeRepository
import com.podium.technicalchallenge.data.repository.HomeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}