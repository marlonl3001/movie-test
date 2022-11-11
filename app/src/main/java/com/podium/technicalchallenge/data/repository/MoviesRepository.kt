package com.podium.technicalchallenge.data.repository

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import com.podium.technicalchallenge.data.MoviesApi
import com.podium.technicalchallenge.data.entity.MovieResponse
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.data.network.queries.Queries
import org.json.JSONObject

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

interface MoviesRepository {
    suspend fun getMovies(): MovieResponse?
}

class MoviesRepositoryImpl(
    private val api: MoviesApi
): MoviesRepository {

    override suspend fun getMovies(): MovieResponse? {
        val paramObject = JSONObject()
        paramObject.put(
            "query", Queries.moviesQuery
        )

        val response = api.postGetMovies(paramObject.toString())
        return response.jsonToObject<MovieResponse>()

//        val jsonBody = response.body()
//        val data = Gson().fromJson(jsonBody, MovieResponse::class.java)
//        return if (data != null) {
//            Result.Success(data.data.movies)
//        } else {
//            Result.Error(java.lang.Exception())
//        }
    }
}