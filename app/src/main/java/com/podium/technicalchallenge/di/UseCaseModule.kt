package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.domain.usecase.MoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MoviesUseCase(get()) }
}