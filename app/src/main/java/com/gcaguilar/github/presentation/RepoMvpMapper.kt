package com.gcaguilar.github.presentation

import com.gcaguilar.github.R
import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.domain.entity.RepoEntity
import javax.inject.Inject

class RepoMvpMapper @Inject constructor(
    private val ownerMvpMapper: OwnerMvpMapper
) : Mapper<RepoEntity, RepoMvp> {
    override fun map(input: RepoEntity): RepoMvp = RepoMvp(
        name = input.name,
        backgroundColor = if (input.fork) R.color.green else R.color.white,
        description = input.description,
        ownerMvp = ownerMvpMapper.map(input.ownerEntity)
    )
}