package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.podium.technicalchallenge.databinding.FragmentHomeBinding
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.presentation.adapter.GenresAdapter
import com.podium.technicalchallenge.presentation.adapter.MoviesAdapter
import com.podium.technicalchallenge.presentation.binding.ViewBinding.bindLoadImage
import com.podium.technicalchallenge.utils.extension.navigateTo
import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import com.podium.technicalchallenge.utils.SpacesItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var topMoviesAdapter: MoviesAdapter
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter

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
                setRecommendedMovie(it?.random())
            }

            genresList.observe(viewLifecycleOwner) {
                genresAdapter.submitList(it)
            }

            errorApi.observe(viewLifecycleOwner) {

            }

            getMovies()
            getTopMovies()
            getGenres()
        }
    }

    private fun setupRecyclerView() {
        topMoviesAdapter = MoviesAdapter(onMovieClickListener())
        moviesAdapter = MoviesAdapter(onMovieClickListener())
        genresAdapter = GenresAdapter()

        binding?.apply {
            recyclerTopMovies.addItemDecoration(SpacesItemDecoration())
            recyclerGenres.addItemDecoration(SpacesItemDecoration(16))
            recyclerBrowseAll.addItemDecoration(SpacesItemDecoration(spanCount = 2))

            topFiveMoviesAdapter = topMoviesAdapter
            allMoviesAdapter = moviesAdapter
            homeGenresAdapter = genresAdapter
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
}