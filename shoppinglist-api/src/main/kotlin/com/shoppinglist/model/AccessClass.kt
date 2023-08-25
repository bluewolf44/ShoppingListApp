package com.shoppinglist.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
@Serializable
data class AccessClass(
    val username:String,
    val type:String,
    val listID:Int
)

object Accesses: Table(){
    val username = varchar("firstName",255).references(Persons.username)
    val type = varchar("firstName",255).references(TypeOfAccesses.accessType)
    val listID = integer("listId").references(Lists.listID)

    override val primaryKey = PrimaryKey(username,listID)
}