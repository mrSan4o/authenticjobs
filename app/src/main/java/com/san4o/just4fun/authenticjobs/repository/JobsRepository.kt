package com.san4o.just4fun.authenticjobs.repository

import com.san4o.just4fun.authenticjobs.repository.network.Api


class JobsRepository(
    private val api: Api
) {
    private val API_KEY = "52db615a841ea7a086e31ff1cbe1ba06"

    suspend fun findJobs(): List<JobItem> {
        return api.findJobs(
            apiKey = API_KEY,
            format = "json",
            method = "aj.jobs.search"
        ).listings?.listing?.map {
            JobItem(
                id = it.id,
                name = it.title,
                postDate = it.postDate
            )
        } ?: emptyList()
    }

    suspend fun getJob(id: String): Job {
        return api.getJob(
            apiKey = API_KEY,
            format = "json",
            method = "aj.jobs.get",
            id = id
        ).let {
            Job(
                id = id,
                name = it.listing.title,
                description = it.listing.description,
                company = it.listing.company.name,
                type = it.listing.type.name,
                postDate = it.listing.postDate
            )
        }
    }

}
