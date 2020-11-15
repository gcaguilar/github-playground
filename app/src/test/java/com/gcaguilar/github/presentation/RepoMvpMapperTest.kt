package com.gcaguilar.github.presentation

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.gcaguilar.github.dummy.OwnerDummy
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class RepoMvpMapperTest {
    private lateinit var repoMvpMapper: RepoMvpMapper
    private val ownerMvpMapper: OwnerMvpMapper = mock()

    @Before
    fun before() {
        repoMvpMapper = RepoMvpMapper(ownerMvpMapper)
    }

    @Test
    fun `Should map to repo mvp given valid repo entity `() {
        val repo = RepoDummy.repoEntity
        whenever(ownerMvpMapper.map(repo.ownerEntity)).thenReturn(OwnerDummy.ownerMvp)

        val result = repoMvpMapper.map(repo)

        assertThat(result).isEqualTo(RepoDummy.repoMvp)
    }

    @Test
    fun `Should return not equal repo mvp given different repo entity `() {
        val repo = RepoDummy.otherRepoEntity
        whenever(ownerMvpMapper.map(repo.ownerEntity)).thenReturn(OwnerDummy.ownerMvp)

        val result = repoMvpMapper.map(repo)

        assertThat(result).isNotEqualTo(RepoDummy.repoMvp)
    }

    @Test
    fun `Should map to list of repo mvp given valid repo entity list `() {
        val repoList = RepoDummy.repoEntityList
        whenever(ownerMvpMapper.map(OwnerDummy.ownerEntity)).thenReturn(OwnerDummy.ownerMvp)

        val result = repoMvpMapper.map(repoList)

        assertThat(result).isEqualTo(RepoDummy.repoMvpList)
    }
}