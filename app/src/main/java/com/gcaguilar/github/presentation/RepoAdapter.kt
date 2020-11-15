package com.gcaguilar.github.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gcaguilar.github.databinding.ItemRepoBinding

class RepoAdapter(
    private val context: Context
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    lateinit var binding: ItemRepoBinding
    private var repositoryList = ArrayList<RepoMvp>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repositoryList[position])

    override fun getItemCount(): Int = repositoryList.size

    fun setRepoList(list: List<RepoMvp>) {
        repositoryList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoMvp) {
            with(binding) {
                textRepositoryName.text = repo.name
                textRepositoryDescription.text = repo.description
                textRepositoryAuthor.text = repo.ownerMvp.name
                root.setBackgroundColor(ContextCompat.getColor(context, repo.backgroundColor))
                imageRepositoryOwner.load(repo.ownerMvp.avatarUrl)
            }
        }
    }
}