package com.gcaguilar.github.data.model

data class RepoModel(val id: Int,
                     val name: String,
                     val fork: Boolean,
                     val description: String,
                     val ownerModel: OwnerModel)