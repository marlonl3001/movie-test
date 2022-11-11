package com.podium.technicalchallenge.domain.mapper

import com.podium.technicalchallenge.data.entity.MovieResponse
import com.podium.technicalchallenge.domain.entity.MovieEntity

object MoviesMapper {
    fun MovieResponse.toMoviesList(): List<MovieEntity> = this.data.movies
}