package com.gcaguilar.github.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoModel(val id: Int,
                     val name: String,
                     val fork: Boolean,
                     val description: String,
                     @Json(name ="owner")
                     val ownerModel: OwnerModel)