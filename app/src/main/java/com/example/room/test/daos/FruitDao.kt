package com.example.room.test.daos

import androidx.room.*
import com.example.room.test.bean.Fruit
import com.example.room.test.bean.User

@Dao
interface FruitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fruits: Fruit?): List<Long?>?

    @Delete
    fun deleteFruits(vararg fruits: Fruit)

    @Query("DELETE from fruit")
    fun deleteAllFruits()

    @Update
    fun updateFruits(vararg fruits: Fruit)

    @Query("SELECT * from fruit")
    fun loadAllFruits(): List<Fruit?>?

    @Query("SELECT * FROM fruit WHERE name = :name")
    fun findFruitsByName(name: String): List<Fruit?>?

    @Query("SELECT * FROM fruit WHERE id = :id")
    fun findFruitsById(id: Int): Fruit?

}