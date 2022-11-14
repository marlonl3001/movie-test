package com.podium.technicalchallenge.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.databinding.ActivityMainBinding
import com.podium.technicalchallenge.presentation.viewmodel.HomeViewModel
import com.podium.technicalchallenge.presentation.viewmodel.MoviesByGenreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val moviesByGenreViewModel: MoviesByGenreViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupViewModels()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
    }

    private fun setupViewModels() {
        with(homeViewModel) {
            loading.observe(this@MainActivity) {
                binding.loading = it
            }
            apiError.observe(this@MainActivity) {
                binding.error = it.errors.firstOrNull()
            }
        }

        with(moviesByGenreViewModel) {
            loading.observe(this@MainActivity) {
                binding.loading = it
            }
            apiError.observe(this@MainActivity) {
                binding.error = it.errors.firstOrNull()
            }
        }
    }
}