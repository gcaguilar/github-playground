package com.gcaguilar.github.domain

import com.gcaguilar.github.common.PostExecutionThread
import com.gcaguilar.github.common.ThreadExecutor
import com.gcaguilar.github.data.datasources.RemoteDataSource
import com.gcaguilar.github.domain.entity.RepoEntity
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import java.lang.IllegalArgumentException
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetPublicReposByOrgTest {
    private lateinit var getPublicReposByOrg: GetPublicReposByOrg

    private val repoRepositoryMock: GithubRepoRepository = mock()
    private val threadExecutorMock: ThreadExecutor = mock()
    private val postThreadExecutorMock: PostExecutionThread = mock()

    @Before
    fun setup() {
        getPublicReposByOrg =
            GetPublicReposByOrg(repoRepositoryMock, threadExecutorMock, postThreadExecutorMock)
    }

    @Test
    fun `Should get public repos when give valid organization name`() {
        val params = Params("Xing")
        whenever(repoRepositoryMock.getRepositoriesByOrg(params.orgName)).thenReturn(Single.just(RepoDummy.repoEntityList))

        val testObserver: TestObserver<List<RepoEntity>> = getPublicReposByOrg.buildUseCaseObservable(params).test()

        verify(repoRepositoryMock).getRepositoriesByOrg(eq(params.orgName))
        testObserver.assertValue(RepoDummy.repoEntityList)
        testObserver.dispose()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception when not give organization name`() {
        val params = null

        val testObserver: TestObserver<List<RepoEntity>> = getPublicReposByOrg.buildUseCaseObservable(params).test()

        verify(repoRepositoryMock, never()).getRepositoriesByOrg(eq(anyString()))
        testObserver.dispose()
    }
}