package com.podium.technicalchallenge.domain.entity

import android.os.Parcelable
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import br.com.mdr.base.extension.getYearFromDate
import br.com.mdr.base.extension.toDate
import kotlinx.parcelize.Parcelize

private const val AVERAGE_DIFF = 10
private const val GENRE_TEXT = "<b>Genre:</b> "
private const val DIRECTOR_TEXT = "<b>Director:</b> \n"

@Parcelize
data class MovieEntity(
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Float,
    val overview: String?,
    val popularity: Float,
    val voteCount: Int,
    val budget: Int,
    val genres: List<String>,
    val director: Director,
    val cast: List<Cast>?
): Parcelable {

    fun getStringAverage() = voteAverage.toString()
    fun getRatingAverage() = voteAverage/AVERAGE_DIFF
    fun getDirectorName() = HtmlCompat.fromHtml(DIRECTOR_TEXT + director?.name, FROM_HTML_MODE_LEGACY)
    fun getReleaseYear(): String {
        val date = releaseDate.toDate()
        val year = date?.getYearFromDate()
        return year?.toString() ?: ""
    }
    fun getGenresList(): Spanned {
        var genresString = GENRE_TEXT

        if (genres != null) {
            for (genre in genres) {
                genresString += genre + if (genre != genres.last()) ", " else ""
            }
        }
        return HtmlCompat.fromHtml(genresString, FROM_HTML_MODE_LEGACY)
    }
}
