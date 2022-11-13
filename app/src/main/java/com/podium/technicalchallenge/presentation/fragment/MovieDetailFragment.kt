package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.databinding.FragmentMovieDetailBinding
import com.podium.technicalchallenge.presentation.adapter.CastAdapter
import com.podium.technicalchallenge.utils.SpacesItemDecoration
import com.podium.technicalchallenge.utils.extension.COLLAPSED_LINES
import com.podium.technicalchallenge.utils.extension.isCollapsed
import com.podium.technicalchallenge.utils.extension.pop

class MovieDetailFragment : Fragment() {

    private var binding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setupView()
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
            android.R.id.home -> pop()
        }
        return true
    }

    private fun setupView() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding?.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = null
        }

        fetchMovieData()
        setupClickListeners()
    }

    private fun fetchMovieData() {
        arguments?.let { bundle ->
            binding?.apply {
                val movieObject = MovieDetailFragmentArgs.fromBundle(bundle).movie
                movie = movieObject
                recyclerCast.addItemDecoration(SpacesItemDecoration())
                recyclerCast.adapter = CastAdapter(movieObject.cast ?: listOf())
            }
        }
    }

    private fun setupClickListeners() {
        binding?.txtCollapse?.setOnClickListener {
            manageTextViewCollapse()
        }
    }

    private fun manageTextViewCollapse() {
        binding?.apply {
            val isCollapsed = txtMovieDescription.isCollapsed()
            txtCollapse.text = getString(
                if (isCollapsed)
                    R.string.collapse
                else
                    R.string.expand
            )

            val maxLines =
                if (isCollapsed) {
                    Integer.MAX_VALUE
                } else {
                    COLLAPSED_LINES
                }

            txtMovieDescription.maxLines = maxLines
        }
    }
}