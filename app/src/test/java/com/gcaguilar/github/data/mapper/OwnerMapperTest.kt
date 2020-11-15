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
}