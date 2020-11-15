package com.gcaguilar.github.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gcaguilar.github.domain.GetPublicReposByOrg
import com.gcaguilar.github.domain.Params
import io.reactivex.disposables.Disposable

private const val ORG_NAME = "Xing"

class MainViewModel(
    private val repoMapper: RepoMvpMapper,
    private val getPublicReposByOrg: GetPublicReposByOrg
) : ViewModel() {
    private val _repoListStates: MutableLiveData<RepoListStates> = MutableLiveData()

    private var disposable: Disposable? = null

    fun getRepos(): LiveData<RepoListStates> = _repoListStates

    fun loadData() {
        _repoListStates.value = RepoListStates.Loading

        disposable = getPublicReposByOrg.execute(Params(ORG_NAME))
            .subscribe({
                _repoListStates.value = if (it.isEmpty()) {
                    RepoListStates.EmptyContent
                } else {
                    RepoListStates.Success(repoMapper.map(it))
                }
            }, {
                val message = it.message ?: "Unknown error"
                Log.e("Error", it.cause.toString())
                _repoListStates.value = RepoListStates.Error(message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}