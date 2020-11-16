package com.gcaguilar.github.domain

import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single

interface GithubRepoRepository {
    fun getRepositoriesByOrg(orgName: String): Single<List<RepoEntity>>
}