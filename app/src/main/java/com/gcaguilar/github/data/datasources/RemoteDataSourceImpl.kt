package com.gcaguilar.github.data.datasources

import com.gcaguilar.github.data.GithubService
import com.gcaguilar.github.data.model.RepoModel
import io.reactivex.Single

class RemoteDataSourceImpl constructor(
    private val githubService: GithubService,
) : RemoteDataSource {
    override fun getPublicReposByOrg(orgName: String): Single<List<RepoModel>> {
        return githubService.getPublicReposByOrg(org = orgName, token = null)
    }
}