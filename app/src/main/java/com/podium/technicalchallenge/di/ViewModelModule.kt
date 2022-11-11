package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.presentation.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DemoViewModel(get()) }
}