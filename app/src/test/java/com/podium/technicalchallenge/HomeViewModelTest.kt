package com.podium.technicalchallenge

import androidx.test.filters.SmallTest
import br.com.mdr.test.base.BaseViewModelTest
import br.com.mdr.test.extension.test
import br.com.mdr.test.mocks.genresResponse
import br.com.mdr.test.mocks.movieResponse
import com.podium.technicalchallenge.data.repository.MainRepository
import com.podium.technicalchallenge.di.useCaseModule
import com.podium.technicalchallenge.di.viewModelModule
import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import com.podium.technicalchallenge.presentation.viewmodel.TOP_FIVE_MOVIES
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@KoinApiExtension
@ExperimentalCoroutinesApi
@SmallTest
@RunWith(JUnit4::class)
class HomeViewModelTest: BaseViewModelTest() {
    private val moviesFirstPage = 30
    private val viewModel: HomeViewModel by inject()
    private val repository: MainRepository by inject()

    @Before
    fun setUp() {
        loadKoinModules(
            useCaseModule +
                    viewModelModule +
                    module(override = true) {
                        single { mockk<MainRepository>() }
                    }
        )
        observerLoading = viewModel.loading.test()
    }

    @Test
    fun givenSuccess_whenFetchAllMovies_thenDisplayResult() {
        //given
        val observerSuccess = viewModel.allMovies.test()
        val observerError = viewModel.apiError.test()
        coEvery { repository.getMovies(limit = moviesFirstPage) } returns movieResponse

        //when
        viewModel.getMovies()

        //then
        verify { observerSuccess.onChanged(movieResponse?.data?.movies) }
        confirmVerified(observerSuccess, observerError)
    }

    @Test
    fun givenSuccess_whenFetchTopFiveMovies_thenDisplayResult() {
        //given
        val observerSuccess = viewModel.topMovies.test()
        val observerError = viewModel.apiError.test()
        coEvery {
            repository.getMovies(limit = TOP_FIVE_MOVIES, sort = true, orderBy = true)
        } returns movieResponse

        //when
        viewModel.getTopMovies()

        //then
        verify { observerSuccess.onChanged(movieResponse?.data?.movies) }
        confirmVerified(observerSuccess, observerError)
    }

    @Test
    fun givenSuccess_whenFetchGenres_thenDisplayResult() {
        //given
        val observerSuccess = viewModel.genresList.test()
        val observerError = viewModel.apiError.test()
        coEvery { repository.getGenres() } returns genresResponse

        //when
        viewModel.getGenres()

        //then
        verify { observerSuccess.onChanged(genresResponse?.data?.genres) }
        confirmVerified(observerSuccess, observerError)
    }
}