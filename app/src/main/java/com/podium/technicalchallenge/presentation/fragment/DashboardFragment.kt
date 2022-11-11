package com.podium.technicalchallenge.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.podium.technicalchallenge.databinding.FragmentDashboardBinding
import com.podium.technicalchallenge.presentation.DemoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val viewModel: DemoViewModel by viewModel()
    private var binding: FragmentDashboardBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            tvTitle.text = "Podium Challenge"
        }

        viewModel.moviesList.observe(viewLifecycleOwner) {
            binding?.tvTitle?.text = it?.first()?.title
        }

        viewModel.getMovies()
    }

}