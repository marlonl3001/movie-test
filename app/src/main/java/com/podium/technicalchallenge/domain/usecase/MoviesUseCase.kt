package com.podium.technicalchallenge.domain.usecase

import com.podium.technicalchallenge.data.repository.MoviesRepository
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.mapper.MoviesMapper.toMoviesList

class MoviesUseCase(
    private val repository: MoviesRepository
) {

    suspend fun getMovies(): List<MovieEntity>? = repository.getMovies()?.toMoviesList()
}