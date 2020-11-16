package com.gcaguilar.github.domain

import com.gcaguilar.github.common.PostExecutionThread
import com.gcaguilar.github.common.SingleUseCase
import com.gcaguilar.github.common.ThreadExecutor
import com.gcaguilar.github.domain.entity.RepoEntity
import io.reactivex.Single

class GetPublicReposByOrg constructor(
    private val repository: GithubRepoRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<RepoEntity>, Params>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Params?): Single<List<RepoEntity>> {
        return params?.let {
            repository.getRepositoriesByOrg(it.orgName)
        } ?: throw IllegalArgumentException("Cannot get public repos without name")
    }
}

data class Params(val orgName: String)