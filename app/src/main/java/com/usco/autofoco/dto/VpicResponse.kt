package com.usco.autofoco.dto

data class VpicResponse(
    val Count: Int,
    val Message: String,
    val SearchCriteria: String?,
    val Results: List<MakeDto>
)
