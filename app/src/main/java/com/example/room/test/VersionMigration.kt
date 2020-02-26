package com.example.room.test

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @ClassName: VersionMigration
 * @Description: 数据库升级记录
 * @Author: yaodetao
 * @Date: 20-2-26 下午5:13.
 */

/**
 * 数据库v1 => v2 user表增加字段'age'
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER")
    }
}
/**
 * 数据库v2 => v3 新增表'fruit'
 */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `fruit` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT)"
        )
    }
}
