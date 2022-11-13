package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.databinding.MoviesByGenreFragmentBinding
import com.podium.technicalchallenge.domain.entity.MovieEntity
import com.podium.technicalchallenge.presentation.adapter.MoviesAdapter
import com.podium.technicalchallenge.presentation.viewmodel.MoviesByGenreViewModel
import com.podium.technicalchallenge.utils.SpacesItemDecoration
import com.podium.technicalchallenge.utils.extension.navigateTo
import com.podium.technicalchallenge.utils.extension.showBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesByGenreFragment : Fragment() {
    private lateinit var moviesAdapter: MoviesAdapter
    private var binding: MoviesByGenreFragmentBinding? = null
    private val viewModel: MoviesByGenreViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesByGenreFragmentBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            setupView(MoviesByGenreFragmentArgs.fromBundle(it).genre)
        }
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.genre_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                openSortBottomSheet()
            }
        }
        return true
    }

    private fun setupView(genre: String) {
        setupRecyclerView(genre)
        setupViewModel(genre)
    }

    private fun setupViewModel(genre: String) {
        with(viewModel) {
            allMovies.observe(viewLifecycleOwner) {
                moviesAdapter.submitList(it)
                binding?.recyclerMovies?.smoothScrollToPosition(START_POSITION)
            }

            errorApi.observe(viewLifecycleOwner) {

            }

            getMovies(genre)
        }
    }

    private fun setupRecyclerView(genreName: String) {
        moviesAdapter = MoviesAdapter(onMovieClickListener(), false)

        binding?.apply {
            recyclerMovies.addItemDecoration(SpacesItemDecoration(spanCount = 2,
                orientation = GridLayoutManager.VERTICAL))

            adapter = moviesAdapter
            genre = genreName

            (activity as? AppCompatActivity)?.let {
                it.setSupportActionBar(toolbar)
                it.supportActionBar?.setDisplayShowHomeEnabled(true)
            }

        }
    }

    private fun onMovieClickListener(): (movie: MovieEntity) -> Unit = {
        navigateTo(MoviesByGenreFragmentDirections.actionMoviesByGenreFragmentToMovieDetailFragment(it))
    }

    private fun openSortBottomSheet() {
        val bottomSheet = SortBottomSheetFragment().apply {
            itemClick = { sortItem ->
                viewModel.sortMovies(sortItem)
            }
        }
        showBottomSheet(bottomSheet)
    }
}