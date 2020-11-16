package com.gcaguilar.github.data

import com.gcaguilar.github.common.NetworkConnection
import com.gcaguilar.github.data.datasources.LocalDataSource
import com.gcaguilar.github.data.datasources.RemoteDataSource
import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.domain.entity.RepoEntity
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

const val ORG_NAME = "Xing"

class GithubRepositoryImplTest {
    private lateinit var githubRepositoryImpl: GithubRepositoryImpl

    private val remoteDataSource: RemoteDataSource = mock()
    private val localDataSource: LocalDataSource = mock()
    private val repoMapper: RepoMapper = mock()
    private val networkConnection: NetworkConnection = mock()

    @Before
    fun setup() {
        githubRepositoryImpl =
            GithubRepositoryImpl(localDataSource, remoteDataSource, repoMapper, networkConnection)
    }

    @Test
    fun `Should get and save repositories when there are connection`() {
        whenever(networkConnection.isInternetAvailable()).thenReturn(true)
        whenever(remoteDataSource.getPublicReposByOrg(ORG_NAME)).thenReturn(Single.just(RepoDummy.repoModelList))
        whenever(localDataSource.saveRepositories(RepoDummy.repoModelList)).thenReturn(Completable.complete())
        whenever(localDataSource.getRepositories()).thenReturn(Single.just(RepoDummy.repoDbList))
        whenever(repoMapper.mapToEntityList(RepoDummy.repoDbList)).thenReturn(RepoDummy.repoEntityList)

        val testObserver: TestObserver<List<RepoEntity>> =
            githubRepositoryImpl.getRepositoriesByOrg(ORG_NAME).test()

        testObserver.assertValue(RepoDummy.repoEntityList)
        verify(remoteDataSource).getPublicReposByOrg(ORG_NAME)
        verify(localDataSource).saveRepositories(eq(RepoDummy.repoModelList))
        verify(localDataSource).getRepositories()
        testObserver.dispose()
    }

    @Test
    fun `Should get repositories from cache when there aren't connection`() {
        whenever(networkConnection.isInternetAvailable()).thenReturn(false)
        whenever(localDataSource.getRepositories()).thenReturn(Single.just(RepoDummy.repoDbList))
        whenever(repoMapper.mapToEntityList(RepoDummy.repoDbList)).thenReturn(RepoDummy.repoEntityList)

        val testObserver: TestObserver<List<RepoEntity>> =
            githubRepositoryImpl.getRepositoriesByOrg(ORG_NAME).test()

        testObserver.assertValue(RepoDummy.repoEntityList)
        verify(remoteDataSource, never()).getPublicReposByOrg(ORG_NAME)
        verify(localDataSource, never()).saveRepositories(any())
        verify(localDataSource).getRepositories()
        testObserver.dispose()
    }
}