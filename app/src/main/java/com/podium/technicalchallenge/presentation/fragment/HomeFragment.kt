package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.FragmentHomeBinding
import br.com.mdr.base.domain.MovieEntity
import com.podium.technicalchallenge.presentation.adapter.GenresAdapter
import com.podium.technicalchallenge.presentation.adapter.MoviesAdapter
import com.podium.technicalchallenge.presentation.binding.ViewBinding.bindLoadImage
import com.podium.technicalchallenge.utils.extension.navigateTo
import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import com.podium.technicalchallenge.presentation.viewmodel.INITIAL_PAGE_NUMBER
import com.podium.technicalchallenge.utils.SpacesItemDecoration
import com.podium.technicalchallenge.utils.extension.showBottomSheet
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val GENRES_SPACE_DECORATION = 16
const val START_POSITION = 0

class HomeFragment: Fragment() {
    private var isSorting = false
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by sharedViewModel()

    private lateinit var topMoviesAdapter: MoviesAdapter
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSorting = savedInstanceState?.getBoolean(IS_SORTING_KEY) ?: isSorting
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_SORTING_KEY, isSorting)
        super.onSaveInstanceState(outState)
    }

    private fun setupView() {
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        with(viewModel) {
            topMovies.observe(viewLifecycleOwner) {
                topMoviesAdapter.submitList(it)
            }

            allMovies.observe(viewLifecycleOwner) {
                moviesAdapter.submitList(it)
                if (isSorting) {
                    binding?.recyclerBrowseAll?.smoothScrollToPosition(START_POSITION)
                }

                setRecommendedMovie(it?.random())
            }

            genresList.observe(viewLifecycleOwner) {
                genresAdapter.submitList(it)
            }

            if (!isSorting)
                getMovies()

            getTopMovies()
            getGenres()
        }
    }

    private fun setupRecyclerView() {
        topMoviesAdapter = MoviesAdapter(onMovieClickListener())
        moviesAdapter = MoviesAdapter(onMovieClickListener())
        genresAdapter = GenresAdapter(onGenreClickListener())

        binding?.apply {
            recyclerTopMovies.addItemDecoration(SpacesItemDecoration())
            recyclerGenres.addItemDecoration(SpacesItemDecoration(GENRES_SPACE_DECORATION))
            recyclerBrowseAll.addItemDecoration(SpacesItemDecoration(spanCount = 2))
            recyclerBrowseAll.addOnScrollListener(onScrollListener())

            topFiveMoviesAdapter = topMoviesAdapter
            allMoviesAdapter = moviesAdapter
            homeGenresAdapter = genresAdapter

            btnSort.setOnClickListener { openSortBottomSheet() }
        }
    }

    private fun setRecommendedMovie(movie: MovieEntity?) {
        binding?.apply {
            imgRecommendedMovie.bindLoadImage(movie?.posterPath)
            txtMovieTitle.text = movie?.title
        }
    }

    private fun onMovieClickListener(): (movie: MovieEntity) -> Unit = {
        navigateTo(HomeFragmentDirections.actionHomeToMovieDetailFragment(it))
    }

    private fun onGenreClickListener(): (genre: String) -> Unit = {
        navigateTo(HomeFragmentDirections.actionHomeToMoviesByGenreFragment(it))
    }

    private fun openSortBottomSheet() {
        val bottomSheet = SortBottomSheetFragment().apply {
            itemClick = { sortItem ->
                viewModel.sortMovies(sortItem)
            }
        }
        showBottomSheet(bottomSheet)
        isSorting = true
    }

    private fun onScrollListener() = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (recyclerView.canScrollHorizontally(RecyclerView.HORIZONTAL).not() && !isSorting) {
                viewModel.getMovies()
            }
        }
    }
}