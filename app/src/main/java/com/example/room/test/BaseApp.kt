package com.example.room.test

import android.app.Application
import androidx.room.Room
import com.example.room.test.daos.MIGRATION_1_2

class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()
        mAppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "testDatabase")
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_1_2)
            .build()
    }


    companion object {
        private var mAppDatabase: AppDatabase? = null
        fun getDatabase(): AppDatabase = mAppDatabase!!
    }
}