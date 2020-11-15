package com.gcaguilar.github.data

import com.gcaguilar.github.data.model.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ORGS = "orgs/"
private const val REPO = "repos"

interface GithubService {
    @GET("$ORGS{org}$REPO")
    fun getPublicReposByOrg(
        @Path("org") org: String,
        @Query("access_token") token: String?
    ): Single<List<RepoModel>>
}