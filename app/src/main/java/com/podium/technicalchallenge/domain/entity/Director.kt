package com.podium.technicalchallenge.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Director(
    val name: String
): Parcelable
