package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.GenreMovieItemBinding
import com.podium.technicalchallenge.databinding.HomeMovieItemBinding
import com.podium.technicalchallenge.domain.entity.MovieEntity

private const val MIN_QUERY_CHAR_SIZE = 4
class MoviesAdapter(
    private val onMovieClick: (movie: MovieEntity) -> Unit,
    private val useHomeViewHolder: Boolean = true
):
    ListAdapter<MovieEntity, MoviesAdapter.HomeMoviesViewHolder>(MoviesCallback()) {
    var currentMovieList: List<MovieEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            if (useHomeViewHolder)
                HomeMovieItemBinding.inflate(inflater, parent, false)
            else
                GenreMovieItemBinding.inflate(inflater, parent, false)

        return HomeMoviesViewHolder(binding, onMovieClick)
    }

    override fun onBindViewHolder(holder: HomeMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun filterMovies(query: String) {
        var filteredItems = listOf<MovieEntity>()

        if (currentMovieList.isNullOrEmpty())
            currentMovieList = this.currentList

        if (query.length >= MIN_QUERY_CHAR_SIZE) {
            filteredItems = this.currentList.filter { movie ->
                movie.title.contains(query, true) ||
                        movie.genres.contains(query) ||
                        movie.director.name.contains(query, true)}
        }

        this.submitList(
            filteredItems.ifEmpty { currentMovieList }
        )
    }

    class HomeMoviesViewHolder(val binding: ViewDataBinding,
                               private val onMovieClick: (movie: MovieEntity) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            (binding as? HomeMovieItemBinding)?.apply {
                movie = movieEntity
                root.setOnClickListener {
                    onMovieClick.invoke(movieEntity)
                }
            } ?: (binding as? GenreMovieItemBinding)?.apply {
                movie = movieEntity
                root.setOnClickListener {
                    onMovieClick.invoke(movieEntity)
                }
            }
        }
    }

    private class MoviesCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem == newItem
    }
}