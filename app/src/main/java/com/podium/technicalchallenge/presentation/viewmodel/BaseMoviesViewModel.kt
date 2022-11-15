package com.podium.technicalchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.presentation.BaseViewModel
import br.com.mdr.base.domain.MovieEntity
import com.podium.technicalchallenge.domain.entity.SortType

const val INITIAL_PAGE_NUMBER = 30

open class BaseMoviesViewModel : BaseViewModel() {
    protected var pageLimit: Int = INITIAL_PAGE_NUMBER
    protected val mutableMovies = MutableLiveData<List<MovieEntity>?>()
    val allMovies: LiveData<List<MovieEntity>?> = mutableMovies

    fun sortMovies(sortType: SortType) {
        allMovies.value?.apply {
            val sortedMovies =
                when(sortType) {
                    SortType.BUDGET -> this.sortedByDescending { it.budget }
                    SortType.OVERVIEW -> this.sortedByDescending { it.overview }
                    SortType.POPULARITY -> this.sortedByDescending { it.popularity }
                    SortType.RELEASE_DATE -> this.sortedBy { it.releaseDate }
                    SortType.TITLE -> this.sortedBy { it.title }
                    SortType.VOTE_AVERAGE -> this.sortedByDescending { it.voteAverage }
                    SortType.VOTE_COUNT -> this.sortedByDescending { it.voteCount }
                }

            mutableMovies.postValue(sortedMovies)
        }
    }
}