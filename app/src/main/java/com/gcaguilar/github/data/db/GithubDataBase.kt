package com.gcaguilar.github.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Repository::class], version = 1, exportSchema = false)
abstract class GithubDataBase : RoomDatabase() {
    abstract fun repsotioryDao(): RepositoryDao
}