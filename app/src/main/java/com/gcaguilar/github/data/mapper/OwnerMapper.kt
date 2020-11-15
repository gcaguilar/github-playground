package com.gcaguilar.github.data.mapper

import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.data.model.OwnerModel
import com.gcaguilar.github.domain.entity.OwnerEntity

class OwnerMapper : Mapper<OwnerModel, OwnerEntity> {
    override fun map(input: OwnerModel): OwnerEntity = OwnerEntity(
        id = input.id,
        name = input.login,
        avatarUrl = input.avatarUrl
    )
}