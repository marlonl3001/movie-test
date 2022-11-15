package com.podium.technicalchallenge.presentation.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mdr.base.domain.MovieEntity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.databinding.FragmentDashboardBinding
import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DashboardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null
    private val viewModel: HomeViewModel by sharedViewModel()

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

        setupView()
    }

    private fun setupView() {
        setupChart()

        viewModel.allMovies.observe(viewLifecycleOwner) { movies ->
            movies?.let { setupChartData(it) }
        }
    }

    private fun setupChart() {
        binding?.chartMovies?.let { chart ->
            chart.description.isEnabled = false

            // entry label styling
            chart.setEntryLabelColor(R.color.textPrimary)
            chart.setEntryLabelTypeface(Typeface.DEFAULT)

            // enable rotation of the chart by touch
            chart.isRotationEnabled = true
            chart.isHighlightPerTapEnabled = true

            chart.animateY(1400, Easing.EaseInOutQuad)

            val l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(false)
        }
    }

    private fun setupChartData(movies: List<MovieEntity>) {
        val sortedMovies = movies.sortedByDescending { it.voteCount }
        val pieEntries = mutableListOf<PieEntry>()
        val top10Movies = mutableListOf<MovieEntity>()

        for (index in 0..4)
            top10Movies.add(sortedMovies[index])

        for (movie in top10Movies) {
            pieEntries.add(PieEntry(movie.voteCount.toFloat(), movie.title))
        }

        val dataSet = PieDataSet(pieEntries, null)

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors: ArrayList<Int> = ArrayList()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors

        val pieData = PieData(dataSet)
        pieData.setValueTextColor(R.color.textPrimary)
        pieData.setValueTypeface(Typeface.DEFAULT)

        binding?.chartMovies?.apply {
            data = pieData
            highlightValues(null)
            invalidate()
        }
    }
}