package com.gcaguilar.github.data.mapper

import com.gcaguilar.github.common.Mapper
import com.gcaguilar.github.data.db.RepositoryDb
import com.gcaguilar.github.data.model.RepoModel
import com.gcaguilar.github.domain.entity.RepoEntity

class RepoMapper constructor(
    private val ownerMapper: OwnerMapper
) : Mapper<RepoModel, RepoEntity> {
    override fun map(input: RepoModel): RepoEntity =
        RepoEntity(
            id = input.id,
            name = input.name,
            fork = input.fork,
            description = input.description,
            ownerEntity = ownerMapper.map(input.ownerModel)
        )

    fun mapToEntity(repositoryDb: RepositoryDb): RepoEntity = RepoEntity(
        id = repositoryDb.repositoryId,
        name = repositoryDb.name,
        fork = repositoryDb.fork,
        description = repositoryDb.description,
        ownerEntity = ownerMapper.mapToEntity(repositoryDb.ownerDb)
    )

    fun mapToDb(repository: RepoModel): RepositoryDb = RepositoryDb(
        repositoryId = repository.id,
        name = repository.name,
        fork = repository.fork,
        description = repository.description,
        ownerDb = ownerMapper.mapToDb(repository.ownerModel)
    )

    fun mapToEntityList(repositoryDb: List<RepositoryDb>): List<RepoEntity> =
        repositoryDb.map { mapToEntity(it) }

    fun mapToDbList(repoList: List<RepoModel>): List<RepositoryDb> = repoList.map { mapToDb(it) }
}