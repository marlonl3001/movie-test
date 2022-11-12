package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.domain.usecase.HomeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { HomeUseCase(get()) }
}