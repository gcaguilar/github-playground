package com.gcaguilar.github.presentation

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.gcaguilar.github.dummy.OwnerDummy
import org.junit.Before
import org.junit.Test

class OwnerDbMvpMapperTest {
    private lateinit var ownerMvpMapper: OwnerMvpMapper

    @Before
    fun setup() {
        ownerMvpMapper = OwnerMvpMapper()
    }

    @Test
    fun `Should map to owner mvp given valid owner entity `() {
        val result = ownerMvpMapper.map(OwnerDummy.ownerEntity)

        assertThat(result).isEqualTo(OwnerDummy.ownerMvp)
    }

    @Test
    fun `Should return not equal owner mvp given different owner entity `() {
        val result = ownerMvpMapper.map(OwnerDummy.otherOwnerEntity)

        assertThat(result).isNotEqualTo(OwnerDummy.ownerMvp)
    }
}