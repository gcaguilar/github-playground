package com.gcaguilar.github.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryDb(
    @PrimaryKey
    val repositoryId: Int,
    val name: String,
    val fork: Boolean,
    val description: String,
    @Embedded val ownerDb: OwnerDb
)

data class OwnerDb(
    val ownerId: Int,
    val login: String,
    val avatarUrl: String
)