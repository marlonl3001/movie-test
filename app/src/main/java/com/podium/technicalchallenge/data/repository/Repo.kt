package com.podium.technicalchallenge.data.repository

class Repo {

//    suspend fun getMovies(): Result<List<MovieEntity>?> {
//        val paramObject = JSONObject()
//        paramObject.put(
//            "query", Queries.getMoviesQuery()
//        )
//
//        val response = ApiClient.getInstance().provideRetrofitClient().create(MoviesApi::class.java).postGetMovies(paramObject.toString())
//        val jsonBody = response.body()
//        val data = Gson().fromJson(jsonBody, MovieResponse::class.java)
//        return if (data != null) {
//            Result.Success(data.data.movies)
//        } else {
//            Result.Error(java.lang.Exception())
//        }
//    }

    companion object {
        private var INSTANCE: Repo? = null
        fun getInstance() = INSTANCE
            ?: Repo().also {
                INSTANCE = it
            }
    }
}
