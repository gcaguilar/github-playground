package com.gcaguilar.github.dummy

import com.gcaguilar.github.R
import com.gcaguilar.github.data.model.RepoModel
import com.gcaguilar.github.domain.entity.RepoEntity
import com.gcaguilar.github.presentation.RepoMvp

private const val ID = 1296269
private const val NAME = "Hello world"
private const val DESCRIPTION = "This is your first repo!"
private const val OTHER_ID = 1296270
private const val OTHER_NAME = "Second project"
private const val OTHER_DESCRIPTION = "This is your second repo!"

object RepoDummy {
    val repoModel = RepoModel(
        id = ID,
        name = NAME,
        fork = false,
        description = DESCRIPTION,
        ownerModel = OwnerDummy.ownerModel,
    )

    val otherRepoModel = RepoModel(
        id = OTHER_ID,
        name = "Second project",
        fork = true,
        description = "This is your second repo!",
        ownerModel = OwnerDummy.ownerModel,
    )

    val repoEntity = RepoEntity(
        id = ID,
        name = NAME,
        fork = false,
        description = DESCRIPTION,
        ownerEntity = OwnerDummy.ownerEntity,
    )

    val otherRepoEntity = RepoEntity(
        id = OTHER_ID,
        name = OTHER_NAME,
        fork = true,
        description = OTHER_DESCRIPTION,
        ownerEntity = OwnerDummy.ownerEntity,
    )

    val repoMvp = RepoMvp(
        name = NAME,
        backgroundColor = R.color.white,
        description = DESCRIPTION,
        ownerMvp = OwnerDummy.ownerMvp,
    )

    private val otherRepoMvp = RepoMvp(
        name = OTHER_NAME,
        backgroundColor = R.color.green,
        description = OTHER_DESCRIPTION,
        ownerMvp = OwnerDummy.ownerMvp,
    )

    val repoModelList = listOf(repoModel, otherRepoModel)
    val repoEntityList = listOf(repoEntity, otherRepoEntity)
    val repoMvpList = listOf(repoMvp, otherRepoMvp)
}