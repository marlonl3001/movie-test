package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.domain.usecase.MainUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MainUseCase(get()) }
}