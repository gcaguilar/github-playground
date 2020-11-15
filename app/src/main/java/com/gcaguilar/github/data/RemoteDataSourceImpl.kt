package com.gcaguilar.github.data

import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.domain.RemoteDataSource
import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single

class RemoteDataSourceImpl constructor(
    private val githubService: GithubService,
    private val repoMapper: RepoMapper
) : RemoteDataSource {
    override fun getPublicReposByOrg(orgName: String): Single<List<RepoEntity>> {
        return githubService.getPublicReposByOrg(org = orgName, token = null)
            .map { repoMapper.map(it) }
    }
}