package br.com.mdr.base.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val movies: List<MovieEntity>
): Parcelable
