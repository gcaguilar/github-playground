package com.gcaguilar.github.data

import com.gcaguilar.github.data.datasources.RemoteDataSourceImpl
import com.gcaguilar.github.data.model.RepoModel
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    private val githubServiceMock: GithubService = mock()

    @Before
    fun setup() {
        remoteDataSourceImpl = RemoteDataSourceImpl(githubServiceMock)
    }

    @Test
    fun `Should call get public repo by org when execute with valid org name and null token`() {
        val orgName = "xing"
        val response = RepoDummy.repoModelList
        whenever(githubServiceMock.getPublicReposByOrg(orgName, null)).thenReturn(Single.just(response))

        val testObserver: TestObserver<List<RepoModel>> = remoteDataSourceImpl.getPublicReposByOrg(orgName).test()

        testObserver.assertValue(RepoDummy.repoModelList)
        verify(githubServiceMock).getPublicReposByOrg(eq(orgName), isNull())
        testObserver.dispose()
    }
}