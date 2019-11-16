package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ApiWorker {

    private const val apiBaseUrl = "https://authenticjobs.com"
    private const val connectionTimeoutInSec = 15L
    private const val readTimeoutInSec = 20L

    val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(gsonConverter())
        .client(client())
        .build()

    private fun client(): OkHttpClient {

        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .connectTimeout(connectionTimeoutInSec, TimeUnit.SECONDS)
            .readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )

        return httpBuilder.build()
    }

    private fun gsonConverter(): GsonConverterFactory = GsonConverterFactory
        .create(
            GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                .create()
        )
}