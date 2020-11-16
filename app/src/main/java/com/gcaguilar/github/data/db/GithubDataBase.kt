package com.gcaguilar.github.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepositoryDb::class], version = 1, exportSchema = false)
abstract class GithubDataBase : RoomDatabase() {
    abstract fun repsotioryDao(): RepositoryDao
}