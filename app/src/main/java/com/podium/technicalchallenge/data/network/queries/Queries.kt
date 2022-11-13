package com.podium.technicalchallenge.data.network.queries

object Queries {
    fun moviesQuery(genre: String? = null, limit: Int, orderBy: Boolean = false,
                    sort: Boolean = false): String {
        var moviesParameters = "movies(limit:${limit}"

        genre?.let { moviesParameters += ", genre: \"$genre\""}

        if (orderBy) moviesParameters += ", orderBy:\"voteAverage\""
        if (sort) moviesParameters += ", sort: DESC"

        moviesParameters += ") {"

        val query =
            if (genre == null) {
                """
                    query GetMoviesQuery {
                      $moviesParameters
                        title
                        releaseDate
                        posterPath
                        voteAverage
                        overview
                        popularity
                        voteCount
                        budget
                        genres
                        cast {
                            name
                            profilePath
                        }
                        director {
                          name
                        }
                      }
                    }
                """
            } else {
                """
                    query GetMoviesQuery {
                      $moviesParameters
                        title
                        releaseDate
                        posterPath
                        voteAverage
                        overview
                        popularity
                        voteCount
                        budget
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
