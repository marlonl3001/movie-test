package com.podium.technicalchallenge.data

import retrofit2.http.Body
import retrofit2.http.POST

interface MoviesApi {

    @POST("graphql")
    suspend fun getMovies(@Body body: String): String

    @POST("graphql")
    suspend fun getGenres(@Body body: String): String
}
