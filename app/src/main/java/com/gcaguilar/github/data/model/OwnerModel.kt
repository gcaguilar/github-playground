package com.gcaguilar.github.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerModel(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)