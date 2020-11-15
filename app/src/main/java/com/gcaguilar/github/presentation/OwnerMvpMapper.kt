package com.gcaguilar.github.presentation

import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.domain.entity.OwnerEntity

class OwnerMvpMapper : Mapper<OwnerEntity, OwnerMvp> {
    override fun map(input: OwnerEntity): OwnerMvp = OwnerMvp(
        input.id,
        input.name,
        input.avatarUrl
    )
}