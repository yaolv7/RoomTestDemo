package com.example.room.test.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @ClassName: Fruit
 * @Description:
 * @Author: yaodetao
 * @Date: 20-2-26 下午5:24.
 */
@Entity
data class Fruit(
    // 主键  autoGenerate = true  自增
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null
) {
    override fun toString(): String {
        return "Fruit(id=$id, name=$name) \n"
    }
}