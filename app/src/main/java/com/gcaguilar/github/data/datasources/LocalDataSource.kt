package com.gcaguilar.github.data.datasources

import com.gcaguilar.github.data.db.RepositoryDb
import com.gcaguilar.github.data.model.RepoModel
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {
    fun getRepositories(): Single<List<RepositoryDb>>
    fun saveRepositories(repositoriesList: List<RepoModel>): Completable
}