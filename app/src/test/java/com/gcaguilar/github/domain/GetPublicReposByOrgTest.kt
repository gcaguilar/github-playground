package com.gcaguilar.github.domain

import com.gcaguilar.github.common.PostExecutionThread
import com.gcaguilar.github.common.ThreadExecutor
import com.gcaguilar.github.domain.entity.RepoEntity
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import java.lang.IllegalArgumentException
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetPublicReposByOrgTest {
    private lateinit var getPublicReposByOrg: GetPublicReposByOrg

    private val remoteDataSourceMock: RemoteDataSource = mock()
    private val threadExecutorMock: ThreadExecutor = mock()
    private val postThreadExecutorMock: PostExecutionThread = mock()

    @Before
    fun setup() {
        getPublicReposByOrg =
            GetPublicReposByOrg(remoteDataSourceMock, threadExecutorMock, postThreadExecutorMock)
    }

    @Test
    fun `Should get public repos when give valid organization name`() {
        val params = Params("Xing")
        whenever(remoteDataSourceMock.getPublicReposByOrg(params.orgName)).thenReturn(Single.just(RepoDummy.repoEntityList))

        val testObserver: TestObserver<List<RepoEntity>> = getPublicReposByOrg.buildUseCaseObservable(params).test()

        verify(remoteDataSourceMock).getPublicReposByOrg(eq(params.orgName))
        testObserver.assertValue(RepoDummy.repoEntityList)
        testObserver.dispose()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception when not give organization name`() {
        val params = null

        val testObserver: TestObserver<List<RepoEntity>> = getPublicReposByOrg.buildUseCaseObservable(params).test()

        verify(remoteDataSourceMock, never()).getPublicReposByOrg(eq(anyString()))
        testObserver.dispose()
    }
}