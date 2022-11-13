package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.podium.technicalchallenge.databinding.FragmentSortBottomSheetBinding
import com.podium.technicalchallenge.domain.entity.SortType
import com.podium.technicalchallenge.presentation.adapter.SortItemsAdapter
import com.podium.technicalchallenge.utils.SpacesItemDecoration

private val sortList = intArrayOf(0, 1, 2, 3, 4, 5, 6)

class SortBottomSheetFragment: BottomSheetDialogFragment() {

    private var binding: FragmentSortBottomSheetBinding? = null
    var itemClick: ((SortType) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortBottomSheetBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.apply {
            recyclerSort.addItemDecoration(
                SpacesItemDecoration(orientation = GridLayoutManager.VERTICAL)
            )
            recyclerSort.adapter = SortItemsAdapter(getSortItems()) {
                itemClick?.invoke(it)
                dismiss()
            }
        }
    }

    private fun getSortItems(): List<SortType> {
        val sortItems = mutableListOf<SortType>()

        for (index in sortList) {
            val sortItem =
                when (index) {
                    0 -> SortType.BUDGET
                    1 -> SortType.OVERVIEW
                    2 -> SortType.POPULARITY
                    3 -> SortType.RELEASE_DATE
                    4 -> SortType.TITLE
                    5 -> SortType.VOTE_AVERAGE
                    6 -> SortType.VOTE_COUNT
                    else -> SortType.BUDGET
                }

            sortItems.add(sortItem)
        }

        return sortItems
    }

}