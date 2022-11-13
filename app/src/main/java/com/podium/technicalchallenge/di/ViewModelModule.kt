package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import com.podium.technicalchallenge.presentation.viewmodel.MoviesByGenreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MoviesByGenreViewModel(get()) }
}