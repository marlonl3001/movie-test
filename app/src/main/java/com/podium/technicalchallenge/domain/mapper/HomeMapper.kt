package com.podium.technicalchallenge.domain.mapper

import br.com.mdr.base.data.entity.GenresResponse
import br.com.mdr.base.data.entity.MovieResponse
import br.com.mdr.base.domain.MovieEntity

object HomeMapper {
    fun MovieResponse.toMoviesList(): List<MovieEntity> = this.data.movies

    fun GenresResponse.toGenresList(): List<String> = this.data.genres
}