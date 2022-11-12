package com.podium.technicalchallenge.domain.mapper

import com.podium.technicalchallenge.data.entity.GenresResponse
import com.podium.technicalchallenge.data.entity.MovieResponse
import com.podium.technicalchallenge.domain.entity.MovieEntity

object HomeMapper {
    fun MovieResponse.toMoviesList(): List<MovieEntity> = this.data.movies

    fun GenresResponse.toGenresList(): List<String> = this.data.genres
}