package com.gcaguilar.github.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.gcaguilar.github.dummy.OwnerDummy
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class RepoMapperTest {
    private lateinit var repoMapper: RepoMapper
    private val ownerMapperMock: OwnerMapper = mock()

    @Before
    fun before() {
        repoMapper = RepoMapper(ownerMapperMock)
    }

    @Test
    fun `Should map to repo entity given valid repo model `() {
        val repo = RepoDummy.repoModel
        whenever(ownerMapperMock.map(OwnerDummy.ownerModel)).thenReturn(OwnerDummy.ownerEntity)

        val result = repoMapper.map(repo)

        assertThat(result).isEqualTo(RepoDummy.repoEntity)
    }

    @Test
    fun `Should return not equal repo entity given different repo model `() {
        val repo = RepoDummy.otherRepoModel
        whenever(ownerMapperMock.map(OwnerDummy.ownerModel)).thenReturn(OwnerDummy.ownerEntity)

        val result = repoMapper.map(repo)

        assertThat(result).isNotEqualTo(RepoDummy.repoEntity)
    }

    @Test
    fun `Should map to list of repo entities given valid repo model list `() {
        val repoList = RepoDummy.repoModelList
        whenever(ownerMapperMock.map(OwnerDummy.ownerModel)).thenReturn(OwnerDummy.ownerEntity)

        val result = repoMapper.map(repoList)

        assertThat(result).isEqualTo(RepoDummy.repoEntityList)
    }
}
