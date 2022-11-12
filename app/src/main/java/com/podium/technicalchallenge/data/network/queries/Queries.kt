package com.podium.technicalchallenge.data.network.queries

object Queries {
    fun moviesQuery(limit: Int?): String {
        val query =
            if (limit != null) {
                """
                    query GetMoviesQuery {
                      movies(limit:${limit}, orderBy:"voteAverage", sort: DESC) {
                        title
                        releaseDate
                        posterPath
                        voteAverage
                      }
                    }
                """
            } else {
                """
                    query GetMoviesQuery {
                  movies {
                    title
                    releaseDate
                    posterPath
                    voteAverage
                  }
                }
                """
            }
        return query
    }

    const val genresQuery =
        """
            query GetGenresQuery {
              genres
            }
        """
}
