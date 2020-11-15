package com.gcaguilar.github.data.model

import com.squareup.moshi.Json

data class OwnerModel(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)