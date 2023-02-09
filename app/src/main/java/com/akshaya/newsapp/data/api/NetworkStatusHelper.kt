package com.akshaya.newsapp.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.akshaya.newsapp.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusHelper @Inject constructor(@ApplicationContext val context: Context) {
    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }
        }
        return false
    }
}