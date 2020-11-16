package com.gcaguilar.github.data.mapper

import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.data.db.OwnerDb
import com.gcaguilar.github.data.model.OwnerModel
import com.gcaguilar.github.domain.entity.OwnerEntity

class OwnerMapper : Mapper<OwnerModel, OwnerEntity> {
    override fun map(input: OwnerModel): OwnerEntity = OwnerEntity(
        id = input.id,
        name = input.login,
        avatarUrl = input.avatarUrl
    )

    fun mapToEntity(input: OwnerDb): OwnerEntity =
        OwnerEntity(
            id = input.ownerId,
            name = input.login,
            avatarUrl = input.avatarUrl
        )

    fun mapToDb(input: OwnerModel): OwnerDb =
        OwnerDb(
            ownerId = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl
        )
}