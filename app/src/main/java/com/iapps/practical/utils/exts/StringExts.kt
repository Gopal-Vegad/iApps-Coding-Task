package com.iapps.practical.utils.exts

import com.iapps.practical.utils.DATE_PUBLISHED_FORMAT
import com.iapps.practical.utils.DATE_TAKEN_FORMAT
import java.text.SimpleDateFormat
import java.util.Locale

private val formatDateTaken: SimpleDateFormat by lazy {
    SimpleDateFormat(DATE_TAKEN_FORMAT, Locale.getDefault())
}

private val formatDatePublished: SimpleDateFormat by lazy {
    SimpleDateFormat(DATE_PUBLISHED_FORMAT, Locale.getDefault())
}
fun String.toFormatDateTaken() = try {
    formatDateTaken.parse(this)
}catch (e: Exception) {
    null
}

fun String.toFormatDatePublished() = try {
    formatDatePublished.parse(this)
}catch (e: Exception) {
    null
}