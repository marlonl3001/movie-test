package com.podium.technicalchallenge.domain.usecase

import com.podium.technicalchallenge.data.repository.HomeRepository
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.mapper.HomeMapper.toGenresList
import com.podium.technicalchallenge.domain.mapper.HomeMapper.toMoviesList

class HomeUseCase(
    private val repository: HomeRepository
) {

    suspend fun getMovies(limit: Int? = null): List<MovieEntity>? =
        repository.getMovies(limit)?.toMoviesList()

    suspend fun getGenres(): List<String>? = repository.getGenres()?.toGenresList()
}