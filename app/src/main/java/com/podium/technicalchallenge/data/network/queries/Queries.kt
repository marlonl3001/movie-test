package com.podium.technicalchallenge.data.network.queries

object Queries {
    val moviesQuery =
"""
    query GetMoviesQuery {
  movies {
    title
    releaseDate
    posterPath
  }
}
"""
}
