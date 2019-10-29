package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class ListingResult(
    @SerializedName("stat") val stat:String,
    @SerializedName("listings") val listings: Listings?
)

data class Listings(
    val listing: List<JobApiItem>?,
    val total:Int,
    val prepage:Int,
    val page:Int,
    val pages:Int,
    @SerializedName("last_update")
    val lastUpdate:Date

)