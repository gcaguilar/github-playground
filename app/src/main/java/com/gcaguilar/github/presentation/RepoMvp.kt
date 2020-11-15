package com.gcaguilar.github.presentation

import androidx.annotation.ColorRes

data class RepoMvp(
    val name: String,
    @ColorRes val backgroundColor: Int,
    val description: String,
    val ownerMvp: OwnerMvp
)