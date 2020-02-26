package com.example.room.test

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.util.LogUtil
import com.example.room.test.bean.User
import com.example.room.test.daos.UserDao
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * @ClassName: SimpleEntityReadWriteTest
 * @Description:
 * @Author: yaodetao
 * @Date: 20-2-26 下午3:00.
 */
@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        userDao = db.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user = User(firstName = "george", lastName = "777")
        val user2 = User(firstName = "george", lastName = "lastName")
        userDao.insert(user)
        userDao.insert(user2)

        val byName = userDao.findUsersByName("george")

        assertThat(byName?.get(0), equalTo(byName?.get(1)))
    }

    @Test
    @Throws(Exception::class)
    fun updateUserAndReadInList() {
        val user = User(firstName = "george", lastName = "777")
        userDao.insert(user)

        val byName = userDao.findUsersByName("george")
        val getUser = byName?.get(0)
        getUser?.lastName = "666"
        userDao.updateUsers(getUser ?: user)

        assertThat(byName?.get(0), equalTo(user))
    }

    @Test
    @Throws(Exception::class)
    fun deleteUserAndReadInList() {
        val user = User(firstName = "george", lastName = "777")
        val user2 = User(firstName = "george", lastName = "lastName")
        userDao.insert(user)
        userDao.insert(user2)

        val byName = userDao.findUsersByName("george")
        val getUser = byName?.get(0)
        userDao.deleteUsers(getUser ?: user)

        val byName2 = userDao.findUsersByName("george")
        assertThat(byName2?.get(0), equalTo(user))
    }
}
    