package com.san4o.just4fun.authenticjobs.repository.network

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("api")
    suspend fun findJobs(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("method") method: String
    ): ListingResult

    @GET("api")
    suspend fun getJob(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("method") method: String,
        @Query("id") id: String
    ): JobResult


}