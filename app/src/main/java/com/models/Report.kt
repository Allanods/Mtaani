package com.models

data class Report(
    val reportId: String = "",
    val category: String = "",
    val description: String = "",
    val location: String = "",
    val timestamp: Long = 0L,
    val upVotes: Int = 0,
    val downVotes: Int = 0,
    val verified: Boolean = false
)
