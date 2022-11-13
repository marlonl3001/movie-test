package br.com.mdr.base.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val TIMELESS_ISO_8601_FORMAT = "yyyy-MM-dd"

object TimelessISO8601Formatter : SimpleDateFormat(TIMELESS_ISO_8601_FORMAT, Locale.getDefault())

fun String.toDate(): Date? {
    return try {
        TimelessISO8601Formatter.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.getYearFromDate(): Int? {
    return try {
        val calendar = GregorianCalendar()
        calendar.time = this
        calendar.get(Calendar.YEAR)
    } catch (e: ParseException) {
        null
    }
}