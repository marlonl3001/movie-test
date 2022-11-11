package com.podium.technicalchallenge.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber

const val BASE_URL = "https://podium-fe-challenge-2021.netlify.app/.netlify/functions/"

val networkModule = module {

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(get())
            .build()
    }

    // OkHttp Client
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Http Logging Interceptor
    single {
        HttpLoggingInterceptor {
            Timber.d(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Interceptor
    single {
        Interceptor { chain ->
            chain.request().run {
                newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json")
                    .build()
                    .let(chain::proceed)
            }
        }
    }
}
