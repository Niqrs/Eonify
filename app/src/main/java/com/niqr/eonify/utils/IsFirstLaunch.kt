package com.niqr.eonify.utils

import android.app.Activity

fun Activity.isFirstLaunch(): Boolean {
    val mainPreferences = getPreferences(0)
    val isFirstLaunch = mainPreferences.getBoolean("FirstLaunch", true)
    if (isFirstLaunch) mainPreferences.edit().putBoolean("FirstLaunch", false).apply()
    return isFirstLaunch
}