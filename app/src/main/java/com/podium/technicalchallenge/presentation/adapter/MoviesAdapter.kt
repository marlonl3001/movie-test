package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.HomeMovieItemBinding
import com.podium.technicalchallenge.domain.entity.MovieEntity

class MoviesAdapter:
    ListAdapter<MovieEntity, MoviesAdapter.HomeMoviesViewHolder>(MoviesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeMovieItemBinding.inflate(inflater, parent, false)

        return HomeMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeMoviesViewHolder(val binding: HomeMovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
            binding.movie = movie
        }
    }

    private class MoviesCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem == newItem
    }
}