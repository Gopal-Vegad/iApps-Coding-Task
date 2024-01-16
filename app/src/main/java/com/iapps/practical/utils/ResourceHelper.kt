package com.iapps.practical.utils

import android.content.Context
import com.iapps.practical.R
import javax.inject.Inject

class ResourceHelper @Inject constructor(
    private val context: Context
) {
    val noInternetConnection: String
        get() = context.getString(R.string.no_internet_connection)
}