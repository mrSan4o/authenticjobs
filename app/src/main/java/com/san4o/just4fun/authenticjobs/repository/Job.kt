package com.san4o.just4fun.authenticjobs.repository

import java.util.*

data class Job(
    val id: String,
    val name: String,
    val description: String,
    val type: String,
    val company: String,
    val postDate: Date

)
