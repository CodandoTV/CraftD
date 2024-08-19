package com.github.codandotv.craftd.app_sample.domain

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun OkHttpClient.Builder.setSslSocketFactory(): OkHttpClient.Builder {
    val trustAllCerts = getUnsafeTrustManager()
    return try {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        this.sslSocketFactory(
            sslContext.socketFactory,
            getUnsafeTrustManager()[0] as X509TrustManager
        )

        this
    } catch (e: KeyManagementException) {
        throw RuntimeException(e)
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}

private fun getUnsafeTrustManager(): Array<TrustManager> {
    return arrayOf(
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )
}