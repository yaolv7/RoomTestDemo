package com.example.room.test.daos

import androidx.room.*
import com.example.room.test.bean.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: User?): List<Long?>?

    @Delete
    fun deleteUsers(vararg users: User)

    @Update
    fun updateUsers(vararg users: User)

    @Query("SELECT * from user")
    fun loadAllUsers(): List<User?>?

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun findUsersByName(firstName: String): List<User?>?

    @Query("SELECT * FROM user WHERE id = :id")
    fun findUsersById(id: Int): User?

}