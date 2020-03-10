package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class JobResult(
    val listing: Listing,
    @SerializedName("apply_url")
    val applyUrl: String,
    val url: String,
    val stat: String,
    val locationCompany: LocationCompany?
) {
    data class Listing(
        val id: String,
        val title: String,
        val description: String,
        val perks: String,
        @SerializedName("howto_apply")
        val howtoApply: String,
        @SerializedName("post_date")
        val postDate: Date,
        @SerializedName("relocation_assistance")
        val relocation: String,
        val telecommuting: String,
        val category: Category,
        val type: Type,
        val keywords: String,
        val company: Company
    )

    data class Company(
        val id: String,
        val name: String,
        val url: String,
        val type: String
    )

    data class LocationCompany(
        val tagline: String,
        val logo: String,
        val location: Location
    )

    data class Location(
        val id: String,
        val name: String
    )

    data class Category(
        val id: String,
        val name: String
    )

    data class Type(
        val id: String,
        val name: String
    )
}