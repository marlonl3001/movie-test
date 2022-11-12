package com.podium.technicalchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.presentation.BaseViewModel
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.usecase.HomeUseCase

private const val INITIAL_PAGE_NUMBER = 0
private const val TOP_FIVE_MOVIES = 5

class HomeViewModel(private val useCase: HomeUseCase) : BaseViewModel() {

    private val _topMovies = MutableLiveData<List<MovieEntity>?>()
    val topMovies: LiveData<List<MovieEntity>?> = _topMovies

    private val _allMovies = MutableLiveData<List<MovieEntity>?>()
    val allMovies: LiveData<List<MovieEntity>?> = _allMovies

    private val _genresList = MutableLiveData<List<String>?>()
    val genresList: LiveData<List<String>?> = _genresList

    fun getMovies() {
        launch {
            _allMovies.postValue(useCase.getMovies())
        }
    }

    fun getTopMovies() {
        launch {
            _topMovies.postValue(useCase.getMovies(TOP_FIVE_MOVIES))
        }
    }

    fun getGenres() {
        launch {
            _genresList.postValue(useCase.getGenres())
        }
    }
}
