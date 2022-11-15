package com.podium.technicalchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.domain.MovieEntity
import com.podium.technicalchallenge.domain.usecase.MainUseCase

const val TOP_FIVE_MOVIES = 5

class HomeViewModel(private val useCase: MainUseCase) : BaseMoviesViewModel() {

    private val _topMovies = MutableLiveData<List<MovieEntity>?>()
    val topMovies: LiveData<List<MovieEntity>?> = _topMovies

    private val _genresList = MutableLiveData<List<String>?>()
    val genresList: LiveData<List<String>?> = _genresList

    fun getMovies() {
        launch {
            mutableMovies.postValue(useCase.getMovies(limit = pageLimit))
            pageLimit += INITIAL_PAGE_NUMBER
        }
    }

    fun getTopMovies() {
        launch {
            _topMovies.postValue(useCase.getMovies(limit = TOP_FIVE_MOVIES, sort = true, orderBy = true))
        }
    }

    fun getGenres() {
        launch {
            _genresList.postValue(useCase.getGenres())
        }
    }
}
