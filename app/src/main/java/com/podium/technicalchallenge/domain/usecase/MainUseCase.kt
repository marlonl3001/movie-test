package com.podium.technicalchallenge.domain.usecase

import com.podium.technicalchallenge.data.repository.MainRepository
import com.podium.technicalchallenge.data.repository.QUERY_LIMIT
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.mapper.HomeMapper.toGenresList
import com.podium.technicalchallenge.domain.mapper.HomeMapper.toMoviesList

class MainUseCase(
    private val repository: MainRepository
) {

    suspend fun getMovies(genre: String? = null, limit: Int = QUERY_LIMIT, orderBy: Boolean = false,
                          sort: Boolean = false): List<MovieEntity>? =
        repository.getMovies(genre, limit, orderBy, sort)?.toMoviesList()

    suspend fun getGenres(): List<String>? = repository.getGenres()?.toGenresList()
}