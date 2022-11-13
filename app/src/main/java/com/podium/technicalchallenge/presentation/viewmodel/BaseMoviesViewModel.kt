package com.podium.technicalchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.presentation.BaseViewModel
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.domain.entity.SortType

private const val INITIAL_PAGE_NUMBER = 30

open class BaseMoviesViewModel : BaseViewModel() {
    protected var pageLimit: Int = INITIAL_PAGE_NUMBER
    protected val mutableMovies = MutableLiveData<List<MovieEntity>?>()
    val allMovies: LiveData<List<MovieEntity>?> = mutableMovies

    fun sortMovies(sortType: SortType) {
        allMovies.value?.apply {
            val sortedMovies =
                when(sortType) {
                    SortType.BUDGET -> this.sortedBy { it.budget }
                    SortType.OVERVIEW -> this.sortedBy { it.overview }
                    SortType.POPULARITY -> this.sortedBy { it.popularity }
                    SortType.RELEASE_DATE -> this.sortedBy { it.releaseDate }
                    SortType.TITLE -> this.sortedBy { it.title }
                    SortType.VOTE_AVERAGE -> this.sortedBy { it.voteAverage }
                    SortType.VOTE_COUNT -> this.sortedBy { it.voteCount }
                }

            mutableMovies.postValue(sortedMovies)
        }
    }
}