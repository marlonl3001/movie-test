package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.BottomSheetSortItemBinding
import com.podium.technicalchallenge.domain.entity.SortType

class SortItemsAdapter(
    private val sortItems: List<SortType>,
    private val onSortClick: (sort: SortType) -> Unit
): RecyclerView.Adapter<SortItemsAdapter.SortItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BottomSheetSortItemBinding.inflate(inflater, parent, false)

        return SortItemsViewHolder(binding, onSortClick)
    }

    override fun onBindViewHolder(holder: SortItemsViewHolder, position: Int) {
        holder.bind(sortItems[position])
    }

    override fun getItemCount() = sortItems.size

    class SortItemsViewHolder(val binding: BottomSheetSortItemBinding,
                           private val onSortClick: (sortType: SortType) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sortType: SortType) {
            binding.apply {
                txtSort.text = sortType.value
                root.setOnClickListener {
                    onSortClick.invoke(sortType)
                }
            }
        }
    }
}