package com.gcaguilar.github.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class Repository(
    @PrimaryKey
    val repositoryId: Int,
    val name: String,
    val fork: Boolean,
    val description: String,
    @Embedded val owner: Owner
)

data class Owner(
    val ownerId: Int,
    val login: String,
    val avatarUrl: String
)
