package com.podium.technicalchallenge.utils.extension

import androidx.appcompat.widget.AppCompatTextView

const val COLLAPSED_LINES = 4

fun AppCompatTextView.isCollapsed(): Boolean {
    return maxLines == COLLAPSED_LINES
}
