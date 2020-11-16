package com.gcaguilar.github.data.datasources

import com.gcaguilar.github.data.db.RepositoryDb
import com.gcaguilar.github.data.db.RepositoryDao
import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.data.model.RepoModel
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSourceImpl constructor(
    private val repositoryDao: RepositoryDao,
    private val repoMapper: RepoMapper
): LocalDataSource {
    override fun getRepositories(): Single<List<RepositoryDb>> {
        return repositoryDao.getRepositoriesByOrg()
    }

    override fun saveRepositories(repositoriesList: List<RepoModel>): Completable {
        return repositoryDao.insert(repoMapper.mapToDbList(repositoriesList))
    }
}