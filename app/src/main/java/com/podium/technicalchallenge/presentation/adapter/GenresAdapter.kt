package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.MovieGenreTemBinding

class GenresAdapter:
    ListAdapter<String, GenresAdapter.GenresViewHolder>(GenresCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieGenreTemBinding.inflate(inflater, parent, false)

        return GenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenresViewHolder(val binding: MovieGenreTemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: String) {
            binding.genre = genre
        }
    }

    private class GenresCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}