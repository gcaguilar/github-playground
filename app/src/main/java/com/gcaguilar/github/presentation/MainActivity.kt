package com.gcaguilar.github.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcaguilar.github.R
import com.gcaguilar.github.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RepoAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeRecyclerView()
        mainViewModel.getRepos().observe(this, Observer<RepoListStates> {
            handleGithubListState(it)
        })
        mainViewModel.loadData()
        binding.buttonRetry.setOnClickListener { mainViewModel.loadData() }
    }

    private fun initializeRecyclerView() {
        adapter = RepoAdapter(this)
        binding.rvPublicRepo.layoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL, false)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvPublicRepo.addItemDecoration(divider)
        binding.rvPublicRepo.adapter = adapter
    }

    private fun handleGithubListState(state: RepoListStates) = when (state) {
        RepoListStates.Loading -> setupScreenLoadingState()
        RepoListStates.EmptyContent -> setupScreenEmptyContentState()
        is RepoListStates.Error ->  {
            setupScreenErrorState()
            handleError(state.message)
        }
        is RepoListStates.Success -> {
            adapter.setRepoList(state.repositoryList)
            setupScreenSuccessState()
        }
    }

    private fun handleError(message: String?) {
        val errorMessage = message ?: "Unknown error"
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).apply {
            setAction(R.string.retry) { mainViewModel.loadData() }
            show()
        }
    }

    private fun setupScreenLoadingState() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.rvPublicRepo.visibility = View.GONE
        binding.groupEmptyState.visibility = View.GONE
    }

    private fun setupScreenEmptyContentState() {
        binding.progressCircular.visibility = View.GONE
        binding.rvPublicRepo.visibility = View.GONE
        binding.groupEmptyState.visibility = View.VISIBLE
    }

    private fun setupScreenErrorState() {
        binding.progressCircular.visibility = View.GONE
        binding.rvPublicRepo.visibility = View.GONE
        binding.groupEmptyState.visibility = View.GONE
    }

    private fun setupScreenSuccessState() {
        binding.progressCircular.visibility = View.GONE
        binding.rvPublicRepo.visibility = View.VISIBLE
        binding.groupEmptyState.visibility = View.GONE
    }
}