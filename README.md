# ROOM框架部分文档

https://developer.android.google.cn/training/data-storage/room/relationships

---

## 定义一对多关系


即使您不能使用直接关系，Room 仍允许您定义实体之间的外键约束。

例如，如果存在另一个名为 Book 的实体，您可以使用 @ForeignKey 注释定义该实体与 User
实体的关系，如以下代码段所示：


    @Entity(foreignKeys = arrayOf(ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"))
    ))
    data class Book(
        @PrimaryKey val bookId: Int,
        val title: String?,
        @ColumnInfo(name = "user_id") val userId: Int
    )


**由于零个或更多个 Book 实例可以通过 user_id 外键关联到一个 User 实例，因此这会在
User 和 Book 之间构建一对多关系模型。
外键非常强大，可让您指定引用的实体更新后会发生什么。例如，您可以通过在 @ForeignKey
注释中添加 onDelete = CASCADE，在 User 的对应实例删除后告知 SQLite
删除该用户的所有图书。**

---

---

## 创建嵌套对象

有时，您可能希望在数据库逻辑中将某个实体或数据对象表示为一个紧密的整体，即使该对象包含多个字段也是如此。在这些情况下，您可以使用
@Embedded
注释表示要解构到表中其子字段的对象。然后，您可以像查询其他各个列一样查询嵌套字段。

例如，您的 User 类可以包含类型 Address 的字段，该类型表示一组分别名为
street、city、state 和 postCode 的字段。要在表中单独存储组成的列，请在 User 类（使用
@Embedded 注释）中添加 Address 字段，如以下代码段所示：


    data class Address(
        val street: String?,
        val state: String?,
        val city: String?,
        @ColumnInfo(name = "post_code") val postCode: Int
    )

    @Entity
    data class User(
        @PrimaryKey val id: Int,
        val firstName: String?,
        @Embedded val address: Address?
    )


**注意：嵌套字段还可以包含其他嵌套字段。
如果某个实体具有同一类型的多个嵌套字段，您可以通过设置 prefix
属性确保每个列都独一无二。然后，Room 会将提供的值添加到嵌套对象中每个列名称的开头。**

---

## 返回列的子集
大多数情况下，您只需获取实体的几个字段。例如，您的界面可能仅显示用户的名字和姓氏，而不是用户的每一条详细信息。通过仅提取应用界面中显示的列，您可以节省宝贵的资源，并且您的查询也能更快完成。
借助 Room，您可以从查询中返回任何基于 Java 的对象，前提是结果列集合会映射到返回的对象。例如，您可以创建以下基于 Java 的普通对象 (POJO) 来获取用户的名字和姓氏：


    data class NameTuple(
        @ColumnInfo(name = "first_name") val firstName: String?,
        @ColumnInfo(name = "last_name") val lastName: String?
    )


现在，您可以在查询方法中使用此 POJO：


    @Dao
    interface MyDao {
        @Query("SELECT first_name, last_name FROM user")
        fun loadFullName(): List<NameTuple>
    }


Room 知道该查询会返 回 first_name 和 last_name 列的值，并且这些值会映射到
NameTuple 类的字段。因此，Room 可以生成正确的代码。如果查询返回太多的列，或者返回
NameTuple 类中不存在的列，则 Room 会显示一条警告。
