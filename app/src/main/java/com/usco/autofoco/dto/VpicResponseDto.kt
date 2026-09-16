package com.usco.autofoco.dto

data class VpicResponseDto(
    val Count: Int,
    val Message: String,
    val SearchCriteria: String?,
    val Results: List<MakeDto>
)
