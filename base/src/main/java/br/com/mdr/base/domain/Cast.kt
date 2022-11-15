package br.com.mdr.base.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val name: String,
    val profilePath: String
): Parcelable {
    fun formattedName() = name.replace(" ", "\n")
}
