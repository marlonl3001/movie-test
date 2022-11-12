package com.podium.technicalchallenge.domain.entity

data class MovieEntity(
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Float
) {
    fun getStringAverage() = voteAverage.toString()
}
