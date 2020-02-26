package com.example.room.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.room.test.bean.User
import com.example.room.test.daos.UserDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setListener()
    }

    private fun init() {
        // todo 数据库操作需要写成异步方法
        db = BaseApp.getDatabase()
        userDao = db.getUserDao()
    }

    private fun setListener() {
        addUser.setOnClickListener {
            userDao.insert(
                User(
                    firstName = String(Random.nextBytes(1)),
                    lastName = String(Random.nextBytes(2)),
                    age = Random.nextInt(100)
                )
            )
        }

        loadUser.setOnClickListener {
            loadView.text = "${userDao.loadAllUsers()}"
        }

        deleteUser.setOnClickListener {
            userDao.deleteAllUsers()
            loadView.text = "${userDao.loadAllUsers()}"
        }

    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}
