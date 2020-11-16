package com.gcaguilar.github.data

import com.gcaguilar.github.common.NetworkConnection
import com.gcaguilar.github.data.datasources.LocalDataSource
import com.gcaguilar.github.data.datasources.RemoteDataSource
import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.domain.GithubRepoRepository
import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single

class GithubRepositoryImpl constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val repoMapper: RepoMapper,
    private val networkConnection: NetworkConnection
) : GithubRepoRepository {
    override fun getRepositoriesByOrg(orgName: String): Single<List<RepoEntity>> {
        return if (networkConnection.isInternetAvailable()) {
            return remoteDataSource.getPublicReposByOrg(orgName)
                .flatMapCompletable { localDataSource.saveRepositories(it) }
                .andThen(localDataSource.getRepositories().map { repoMapper.mapToEntityList(it) })
        } else {
            localDataSource.getRepositories().map { repoMapper.mapToEntityList(it) }
        }
    }
}