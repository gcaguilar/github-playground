package com.gcaguilar.github.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.gcaguilar.github.dummy.OwnerDummy
import com.gcaguilar.github.dummy.RepoDummy
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class OwnerMapperTest {
    private lateinit var ownerMapper: OwnerMapper

    @Before
    fun before() {
        ownerMapper = OwnerMapper()
    }

    @Test
    fun `Should map to owner entity given valid owner model `() {
        val result = ownerMapper.map(OwnerDummy.ownerModel)

        assertThat(result).isEqualTo(OwnerDummy.ownerEntity)
    }

    @Test
    fun `Should return not equal owner entity given different owner model `() {
        val result = ownerMapper.map(OwnerDummy.otherOwnerModel)

        assertThat(result).isNotEqualTo(OwnerDummy.ownerEntity)
    }

    @Test
    fun `Should map to owner db given valid owner model `() {
        val result = ownerMapper.mapToDb(OwnerDummy.ownerModel)

        assertThat(result).isEqualTo(OwnerDummy.ownerDb)
    }

    @Test
    fun `Should map to owner entity given valid owner db `() {
        val result = ownerMapper.mapToEntity(OwnerDummy.ownerDb)

        assertThat(result).isEqualTo(OwnerDummy.ownerEntity)
    }

    @Test
    fun `Should return not equal owner entity given different owner db `() {
        val result = ownerMapper.mapToEntity(OwnerDummy.otherOwnerDb)

        assertThat(result).isNotEqualTo(OwnerDummy.ownerEntity)
    }

    @Test
    fun `Should return not equal owner model given different owner dbo `() {
        val result = ownerMapper.mapToDb(OwnerDummy.otherOwnerModel)

        assertThat(result).isNotEqualTo(OwnerDummy.ownerDb)
    }
}