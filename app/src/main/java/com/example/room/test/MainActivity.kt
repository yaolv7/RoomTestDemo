package com.example.room.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.room.test.bean.Fruit
import com.example.room.test.bean.User
import com.example.room.test.daos.FruitDao
import com.example.room.test.daos.UserDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
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
        db = BaseApp.getDatabase()
        userDao = db.getUserDao() // 同步方法数据库（阻塞UI线程）
        fruitDao = db.getFruitDao()// 异步数据库(不阻塞UI线程)
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
            GlobalScope.launch {
                fruitDao.insert(
                    Fruit(
                        name = String(Random.nextBytes(1))
                    )
                )
            }
        }

        loadFruit.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                loadView.text = "${fruitDao.loadAllFruits()}"
            }
        }

        deleteFruit.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                fruitDao.deleteAllFruits()
                loadView.text = "${fruitDao.loadAllFruits()}"
            }
        }

        showToast.setOnClickListener {
            Toast.makeText(this, "测试阻塞主线程", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}
