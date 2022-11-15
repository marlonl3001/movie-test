package br.com.mdr.base.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Director(
    val name: String
): Parcelable
