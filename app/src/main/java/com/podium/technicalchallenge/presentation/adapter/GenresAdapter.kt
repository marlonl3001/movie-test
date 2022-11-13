package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.MovieGenreItemBinding

class GenresAdapter(
    private val onGenreClick: (genre: String) -> Unit
):
    ListAdapter<String, GenresAdapter.GenresViewHolder>(GenresCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieGenreItemBinding.inflate(inflater, parent, false)

        return GenresViewHolder(binding, onGenreClick)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenresViewHolder(val binding: MovieGenreItemBinding,
                           private val onGenreClick: (genre: String) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genreName: String) {
            binding.apply {
                genre = genreName
                root.setOnClickListener {
                    onGenreClick.invoke(genreName)
                }
            }
        }
    }

    private class GenresCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}