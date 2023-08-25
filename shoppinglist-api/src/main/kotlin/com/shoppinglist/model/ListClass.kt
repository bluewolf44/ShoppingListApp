package com.shoppinglist.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.time.Instant


@Serializable
data class ListClass(
    val listID:Int,
    val accessType:String,
    val listName:String,
    val listDescription:String,
    val lastUpdated: LocalDateTime,
    val dateCreated:LocalDateTime)

object Lists : Table() {
    val listID = integer("listId")
    val accessType = varchar("firstName",255)
    val listName = varchar("firstName",255)
    val listDescription = varchar("firstName",255)
    val lastUpdated = timestamp("lastUpdated")
    val dateCreated = timestamp("dateCreated")

    override val primaryKey = PrimaryKey(listID)
}