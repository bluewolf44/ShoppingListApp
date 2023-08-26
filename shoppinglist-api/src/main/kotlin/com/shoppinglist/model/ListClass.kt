package com.shoppinglist.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.time.Instant


@Serializable
data class ListClass(
    val listID:Int,
    val listName:String,
    val listDescription:String,
    val text:String,
    val lastUpdated: LocalDateTime,
    val dateCreated:LocalDateTime)

object Lists : Table() {
    val listID = integer("listId")
    val listName = varchar("listName",255)
    val listDescription = varchar("listDescription",255)
    val text = text("text")
    val lastUpdated = timestamp("lastUpdated")
    val dateCreated = timestamp("dateCreated")

    override val primaryKey = PrimaryKey(listID)
}