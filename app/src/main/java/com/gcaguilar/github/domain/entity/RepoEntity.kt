package com.gcaguilar.github.domain.entity

data class RepoEntity(val id: Int,
                      val name: String,
                      val fork: Boolean,
                      val description: String,
                      val ownerEntity: OwnerEntity)