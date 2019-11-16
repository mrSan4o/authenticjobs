package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class ListingResult(
    @SerializedName("stat") val stat: String,
    @SerializedName("listings") val listings: Listings = EmptyListings()
) {
    companion object {
        internal fun EmptyListings() = Listings(
            total = 0,
            prepage = 0,
            page = 0,
            pages = 0,
            lastUpdate = null
        )
    }
}

data class Listings(
    val listing: List<JobApiItem> = emptyList(),
    val total: Int,
    val prepage: Int,
    val page: Int,
    val pages: Int,
    @SerializedName("last_update")
    val lastUpdate: Date?

)