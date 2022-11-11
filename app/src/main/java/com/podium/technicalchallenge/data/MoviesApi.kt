package com.podium.technicalchallenge.data

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MoviesApi {
    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun postGetMovies(@Body body: String): String
}
