package com.sprinter.mobws.repositories.source.network

import com.google.gson.Gson
import com.sprinter.mobws.di.TrustAllCerts
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext


class NetworkApiFactory private constructor(private val builder: Builder) {

    data class Builder(val endPoint: String, var gson: Gson) {

        var isDebug: Boolean = true
            private set

        var connectionTimeOutSeconds: Long = 10
            private set

        fun isDebug(isDebug: Boolean) = apply { this.isDebug = isDebug }

        fun connectionTimeOutSeconds(timeOutSec: Long) =
            apply { this.connectionTimeOutSeconds = timeOutSec }

        fun build(): NetworkApiFactory = NetworkApiFactory(this)
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(builder.endPoint)
            .addConverterFactory(GsonConverterFactory.create(builder.gson))
            .client(client)
            .build()

    private val client: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = if (builder.isDebug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.BASIC

            // StrictMode policy violation: android.os.strictmode.UntaggedSocketViolation:
            // Untagged socket detected; use TrafficStats.setThreadSocketTag() to track all network usage
            // we can create OkHttpClient. and setup in each override method this code:
            // TrafficStats.setThreadStatsTag(THREAD_ID); // where THREAD_ID is 10000 some any long value
            val okHttp = OkHttpClient.Builder()
            okHttp.addInterceptor(logging)

            val lists = listOf(ConnectionSpec.COMPATIBLE_TLS)
            okHttp.connectionSpecs(lists)

            if (builder.isDebug) {
                val trustAllCerts = TrustAllCerts()
                val sslContext = SSLContext.getInstance("SSL")

                sslContext.init(
                    emptyArray(),
                    arrayOf(trustAllCerts),
                    java.security.SecureRandom()
                )

                okHttp.sslSocketFactory(sslContext.socketFactory, trustAllCerts)
                    .hostnameVerifier(HostnameVerifier { _, _ -> true })
            }

            okHttp.connectTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)
                .readTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)
                .writeTimeout(builder.connectionTimeOutSeconds, TimeUnit.SECONDS)
            return okHttp.build()
        }
}
