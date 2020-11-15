package com.gcaguilar.github.dummy

import com.gcaguilar.github.data.model.RepoModel
import com.gcaguilar.github.domain.entity.RepoEntity

object RepoDummy {
    val repoModel = RepoModel(
        id = 1296269,
        name = "Hello world",
        fork = false,
        description = "This is your first repo!",
        ownerModel = OwnerDummy.ownerModel,
    )

    val otherRepoModel = RepoModel(
        id = 1296270,
        name = "Second project",
        fork = true,
        description = "This is your second repo!",
        ownerModel = OwnerDummy.ownerModel,
    )

    val repoEntity = RepoEntity(
        id = 1296269,
        name = "Hello world",
        fork = false,
        description = "This is your first repo!",
        ownerEntity = OwnerDummy.ownerEntity,
    )

    val otherRepoEntity = RepoEntity(
        id = 1296270,
        name = "Second project",
        fork = true,
        description = "This is your second repo!",
        ownerEntity = OwnerDummy.ownerEntity,
    )

    val repoModelList = listOf(repoModel, otherRepoModel)
    val repoEntityList = listOf(repoEntity, otherRepoEntity)
}