package com.example.room.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.room.test.bean.Fruit
import com.example.room.test.bean.User
import com.example.room.test.daos.FruitDao
import com.example.room.test.daos.UserDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var fruitDao: FruitDao

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
        fruitDao = db.getFruitDao()
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

        // -------- Fruit -------
        addFruit.setOnClickListener {
            fruitDao.insert(
                Fruit(
                    name = String(Random.nextBytes(1))
                )
            )
        }

        loadFruit.setOnClickListener {
            loadView.text = "${fruitDao.loadAllFruits()}"
        }

        deleteFruit.setOnClickListener {
            fruitDao.deleteAllFruits()
            loadView.text = "${fruitDao.loadAllFruits()}"
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}
