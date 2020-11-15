package com.gcaguilar.github.domain

import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single

interface RemoteDataSource {
    fun getPublicReposByOrg(orgName: String): Single<List<RepoEntity>>
}