package com.podium.technicalchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.podium.technicalchallenge.databinding.CastListItemBinding
import br.com.mdr.base.domain.Cast

class CastAdapter(
    private val castList: List<Cast>
): RecyclerView.Adapter<CastAdapter.CastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CastListItemBinding.inflate(inflater, parent, false)

        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount() = castList.size

    class CastViewHolder(val binding: CastListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            binding.cast = cast
        }
    }
}