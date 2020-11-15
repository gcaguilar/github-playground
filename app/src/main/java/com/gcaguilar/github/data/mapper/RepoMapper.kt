package com.gcaguilar.github.data.mapper

import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.data.model.RepoModel
import com.gcaguilar.github.domain.entity.RepoEntity

class RepoMapper constructor(
    private val ownerMapper: OwnerMapper
): Mapper<RepoModel, RepoEntity>{
    override fun map(input: RepoModel): RepoEntity =
        RepoEntity(
            id = input.id,
            name = input.name,
            fork = input.fork,
            description = input.description,
            ownerEntity = ownerMapper.map(input.ownerModel)
        )
}