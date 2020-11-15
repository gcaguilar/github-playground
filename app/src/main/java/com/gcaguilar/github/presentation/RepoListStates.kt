package com.gcaguilar.github.presentation

sealed class RepoListStates {
    object Loading: RepoListStates()
    object EmptyContent: RepoListStates()
    data class Error(val message: String?): RepoListStates()
    data class Success(val repositoryList: List<RepoMvp>): RepoListStates()
}