package com.example.room.test.bean

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @ClassName: User
 * @Description:
 * @Author: yaodetao
 * @Date: 20-2-25 下午3:21.
 */

//
//@Entity(primaryKeys = arrayOf("firstName", "lastName")) // 复合主键
//@Entity(tableName = "users") // 不同的表名称（默认是类名）  SQLite 中的表名称不区分大小写。
//@Entity(ignoredColumns = arrayOf("picture")) // 当发生继承时，如B继承A，可以忽略A中的继承的"picture"字段
//@Entity(indices = [Index(value = ["first_name", "last_name"], unique = true)]) // 数据库中的某些字段或字段组必须是唯一的。 可防止表格具有包含 firstName 和 lastName 列的同一组值的两行：
@Entity
data class User(
    // 主键  autoGenerate = true  自增
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
//      @ColumnInfo(name = "first_name") val firstName: String?, // 不同的列名，默认是参数名称
//      @ColumnInfo(name = "last_name") val lastName: String?
    @Ignore var picture: Bitmap? = null,// @Ignore 注解 忽略字段
    var age: Int? = null
) {
    override fun toString(): String {
        return "User(id=$id, firstName=$firstName, lastName=$lastName, picture=$picture, age=$age)\n"
    }
}

