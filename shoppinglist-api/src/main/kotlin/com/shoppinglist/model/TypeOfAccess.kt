package com.shoppinglist.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
@Serializable
data class TypeOfAccess(
    val accessType:String,
)

object TypeOfAccesses: Table(){
    val accessType = varchar("accessType",3)

    override val primaryKey = PrimaryKey(accessType)
}