package com.gcaguilar.github.data.datasources

import com.gcaguilar.github.data.db.RepositoryDao
import com.gcaguilar.github.data.db.RepositoryDb
import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class LocalDataSourceImplTest {
    private val repositoryDaoMock: RepositoryDao = mock()
    private val repoMapperMock: RepoMapper = mock()

    private lateinit var localDataSourceImpl: LocalDataSourceImpl

    @Before
    fun setup() {
        localDataSourceImpl = LocalDataSourceImpl(repositoryDaoMock, repoMapperMock)
    }

    @Test
    fun `Should call get repositories when call getRepositories`() {
        whenever(repositoryDaoMock.getRepositoriesByOrg()).thenReturn(Single.just(RepoDummy.repoDbList))

        val testObserver: TestObserver<List<RepositoryDb>> = repositoryDaoMock.getRepositoriesByOrg().test()

        testObserver.assertValue(RepoDummy.repoDbList)
        verify(repositoryDaoMock).getRepositoriesByOrg()
        testObserver.dispose()
    }

    @Test fun `Should insert repositories when call save repositories`() {
        whenever(repoMapperMock.mapToDbList(RepoDummy.repoModelList)).thenReturn(RepoDummy.repoDbList)
        whenever(repositoryDaoMock.insert(RepoDummy.repoDbList)).thenReturn(Completable.complete())

        val testObserver = repositoryDaoMock.insert(RepoDummy.repoDbList).test()

        testObserver.onComplete()
        verify(repositoryDaoMock).insert(eq(RepoDummy.repoDbList))
        testObserver.dispose()
    }
}