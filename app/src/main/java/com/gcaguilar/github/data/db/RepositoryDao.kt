package com.gcaguilar.github.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositories")
    fun getRepositoriesByOrg(): Single<List<Repository>>

    @Insert
    fun insert(repository: List<Repository>): Completable
}