package com.gcaguilar.github.domain

import com.gcaguilar.github.common.PostExecutionThread
import com.gcaguilar.github.common.SingleUseCase
import com.gcaguilar.github.common.ThreadExecutor
import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single
import javax.inject.Inject

class GetPublicReposByOrg @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<RepoEntity>, Params>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Params?): Single<List<RepoEntity>> {
        return params?.let {
            remoteDataSource.getPublicReposByOrg(it.orgName)
        } ?: throw IllegalArgumentException("Cannot get public repos without name")
    }
}

data class Params(val orgName: String)