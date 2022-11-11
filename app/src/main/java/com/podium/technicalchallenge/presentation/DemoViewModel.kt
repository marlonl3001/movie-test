package com.podium.technicalchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.presentation.BaseViewModel
import com.podium.technicalchallenge.data.repository.MoviesRepository
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.usecase.MoviesUseCase

private const val INITIAL_PAGE_NUMBER = 0

class DemoViewModel(private val useCase: MoviesUseCase) : BaseViewModel() {

    private val _moviesList = MutableLiveData<List<MovieEntity>?>()
    val moviesList: LiveData<List<MovieEntity>?> = _moviesList

    fun getMovies() {
        launch {
            _moviesList.postValue(useCase.getMovies())
        }
//        viewModelScope.launch(Dispatchers.IO) {
//            runCatching {
//                repository.getMovies()
//            }
//                .onSuccess { result ->
//                    _moviesList.postValue(result)
//                }
//                .onFailure {
//
//                }


//            val result = try {
//                Repo.getInstance().getMovies()
//            } catch (e: Exception) {
//                Result.Error(e)
//            }
//            when (result) {
//                is Result.Success<List<MovieEntity>?> -> {
//                    Log.d(TAG, "movies= " + result.data)
//                }
//                else -> {
//                    Log.e(TAG, "movies= " + result)
//                }
//            }
//        }
    }
}
