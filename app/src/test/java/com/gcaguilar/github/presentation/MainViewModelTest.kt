package com.gcaguilar.github.presentation

import androidx.lifecycle.Observer
import com.gcaguilar.github.common.getOrAwaitValue
import com.gcaguilar.github.domain.GetPublicReposByOrg
import com.gcaguilar.github.domain.entity.RepoEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    private val repoMvpMapperMock: RepoMvpMapper = mock()
    private val getPublicReposByOrgMock: GetPublicReposByOrg = mock()
    private val observer: Observer<RepoListStates> = mock()

    @Before
    fun setup() {
        viewModel = MainViewModel(
            repoMvpMapperMock,
            getPublicReposByOrgMock
        )
        viewModel.getRepos().observeForever(observer)
    }

    @Test
    fun `Should show repositories when use case returns correct data`() {
        val repoEntityList = listOf<RepoEntity>(mock())
        val repoMvpList = listOf<RepoMvp>(mock())
        given(getPublicReposByOrgMock.execute(any())).willReturn(Single.just(repoEntityList))
        given(repoMvpMapperMock.map(repoEntityList)).willReturn(repoMvpList)

        viewModel.getRepos().getOrAwaitValue()
        viewModel.loadData()

        verify(observer).onChanged(RepoListStates.Loading)
        verify(observer).onChanged(RepoListStates.Success(repoMvpList))
    }
}