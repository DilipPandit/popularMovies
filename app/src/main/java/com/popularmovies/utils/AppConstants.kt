package com.popularmovies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AppConstants {
    const val KEY="f0fb08eadadd01258ef2d4bd4a3d3b3c"
    const val BASE_URL="https://api.themoviedb.org/"
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}