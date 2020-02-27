package com.example.room.test.daos

import androidx.room.*
import com.example.room.test.bean.Fruit
import com.example.room.test.bean.User

/**
 * suspend 修饰的方法为 挂起方法
 *
 *  GlobalScope.launch(Dispatchers.Main) {
 *              Toast.makeText(this, "测试阻塞主线程", Toast.LENGTH_SHORT).show() // 在主线程运行
 *              fruitDao.deleteAllFruits() // 在协程线程运行，会阻塞当前GlobalScope.launch，不阻塞主线程
 *              loadView.text = "${fruitDao.loadAllFruits()}" // 当deleteAllFruits()运行完后才运行
 *          }
 *
 *  // 挂起方法
 *  suspend fun test()= withContext(Dispatchers.Default){
 *     // do something
 *  }
 */
@Dao
interface FruitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg fruits: Fruit?): List<Long?>?

    @Delete
    suspend fun deleteFruits(vararg fruits: Fruit)

    @Query("DELETE from fruit")
    suspend fun deleteAllFruits()

    @Update
    suspend fun updateFruits(vararg fruits: Fruit)

    @Query("SELECT * from fruit")
    suspend fun loadAllFruits(): List<Fruit?>?

    @Query("SELECT * FROM fruit WHERE name = :name")
    suspend fun findFruitsByName(name: String): List<Fruit?>?

    @Query("SELECT * FROM fruit WHERE id = :id")
    suspend fun findFruitsById(id: Int): Fruit?

}