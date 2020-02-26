package com.example.room.test

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.test.bean.User
import com.example.room.test.daos.UserDao

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}