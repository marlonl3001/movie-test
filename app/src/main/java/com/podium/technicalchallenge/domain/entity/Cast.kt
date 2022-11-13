package com.podium.technicalchallenge.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val name: String,
    val profilePath: String
): Parcelable {
    fun className(): String = this::class.java.name
    fun formattedName() = name.replace(" ", "\n")
}
