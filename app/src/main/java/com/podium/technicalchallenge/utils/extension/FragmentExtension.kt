package com.podium.technicalchallenge.utils.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import timber.log.Timber

fun Fragment.navigateTo(direction: NavDirections) {
    try {
        findNavController().navigate(direction)
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}

fun Fragment.pop() {
    try {
        findNavController().navigateUp()
    } catch (e: IllegalArgumentException) {
        Timber.e(e)
    }
}