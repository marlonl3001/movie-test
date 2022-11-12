package com.podium.technicalchallenge.data.repository

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import com.podium.technicalchallenge.data.MoviesApi
import com.podium.technicalchallenge.data.entity.GenresResponse
import com.podium.technicalchallenge.data.entity.MovieResponse
import com.podium.technicalchallenge.data.network.queries.Queries
import org.json.JSONObject

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

interface HomeRepository {
    suspend fun getMovies(limit: Int? = null): MovieResponse?
    suspend fun getGenres(): GenresResponse?
}

class HomeRepositoryImpl(
    private val api: MoviesApi
): HomeRepository {

    override suspend fun getMovies(limit: Int?): MovieResponse? {
        val paramObject = JSONObject()
        paramObject.put(
            "query", Queries.moviesQuery(limit)
        )

        val response = api.getMovies(paramObject.toString())
        return response.jsonToObject<MovieResponse>()

//        val jsonBody = response.body()
//        val data = Gson().fromJson(jsonBody, MovieResponse::class.java)
//        return if (data != null) {
//            Result.Success(data.data.movies)
//        } else {
//            Result.Error(java.lang.Exception())
//        }
    }

    override suspend fun getGenres(): GenresResponse? {
        val paramObject = JSONObject()
        paramObject.put(
            "query", Queries.genresQuery
        )

        val response = api.getGenres(paramObject.toString())
        return response.jsonToObject<GenresResponse>()
    }
}