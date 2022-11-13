package com.podium.technicalchallenge.presentation.viewmodel

import com.podium.technicalchallenge.domain.usecase.MainUseCase

class MoviesByGenreViewModel(private val useCase: MainUseCase) : BaseMoviesViewModel() {

    fun getMovies(genre: String) {
        launch {
            mutableMovies.postValue(useCase.getMovies(genre = genre, limit = pageLimit))
            pageLimit += INITIAL_PAGE_NUMBER
        }
    }
}