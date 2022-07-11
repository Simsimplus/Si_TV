package io.simsim.iptv.utils

import android.webkit.URLUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import splitties.systemservices.connectivityManager
import java.net.HttpURLConnection
import java.net.URL


suspend fun String.isServerReachable(): Boolean {
    if (!URLUtil.isValidUrl(this)) return false
    val netInfo = connectivityManager.activeNetworkInfo
    return if (netInfo != null && netInfo.isConnected) {
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val urlServer = URL(this@isServerReachable)
                val urlConn: HttpURLConnection = urlServer.openConnection() as HttpURLConnection
                urlConn.connectTimeout = 3000 //<- 3Seconds Timeout
                urlConn.connect()
                urlConn.responseCode == 200
            }.getOrDefault(false)
        }
    } else false
}