package com.podium.technicalchallenge.di

import com.podium.technicalchallenge.data.MoviesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val moviesApiModule = module {
    single { get<Retrofit>().create(MoviesApi::class.java) }
}