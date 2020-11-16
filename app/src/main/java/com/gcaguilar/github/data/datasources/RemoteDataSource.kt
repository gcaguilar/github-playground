package com.gcaguilar.github.data.datasources

import com.gcaguilar.github.data.model.RepoModel
import io.reactivex.Single

interface RemoteDataSource {
    fun getPublicReposByOrg(orgName: String): Single<List<RepoModel>>
}