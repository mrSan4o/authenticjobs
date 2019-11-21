package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class JobApiItem(
    val id: Long,
    val title: String,
    @SerializedName("post_date")
    val postDate: Date,
    val company: JobApiItemCompany
)

data class JobApiItemCompany(
    val id: String,
    val name: String,
    val logo: String
)