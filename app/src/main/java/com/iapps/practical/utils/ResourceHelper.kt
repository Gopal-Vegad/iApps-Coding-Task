package com.iapps.practical.utils

import android.content.Context
import com.iapps.practical.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceHelper @Inject constructor(
    @ApplicationContext val context: Context
) {
    val noInternetConnection: String
        get() = context.getString(R.string.no_internet_connection)
}