package com.gcaguilar.github.data

import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.domain.entity.RepoEntity
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    private val githubServiceMock: GithubService = mock()
    private val repoMapperMock: RepoMapper = mock()

    @Before
    fun setup() {
        remoteDataSourceImpl = RemoteDataSourceImpl(githubServiceMock, repoMapperMock)
    }

    @Test
    fun `Should call get public repo by org when execute with valid org name and null token`() {
        val orgName = "xing"
        val response = RepoDummy.repoModelList
        whenever(githubServiceMock.getPublicReposByOrg(orgName, null)).thenReturn(Single.just(response))
        whenever(repoMapperMock.map(RepoDummy.repoModelList)).thenReturn(RepoDummy.repoEntityList)

        val testObserver: TestObserver<List<RepoEntity>> = remoteDataSourceImpl.getPublicReposByOrg(orgName).test()

        testObserver.assertValue(RepoDummy.repoEntityList)
        verify(githubServiceMock).getPublicReposByOrg(eq(orgName), isNull())
        verify(repoMapperMock).map(eq(response))
        testObserver.dispose()
    }
}