package com.shoppinglist.dao

import com.shoppinglist.model.*
import kotlinx.datetime.LocalDateTime

interface ListDAO {
    suspend fun allList(): List<ListClass>
    suspend fun getListForPerson(username: String,password:String): List<ListClass>
    suspend fun getList (username: String,password:String,listId:Int): ListClass?
    suspend fun addList(list:ListClass): ListClass?
    suspend fun deleteList(username: String,password:String,listId:Int) : Boolean
    suspend fun updateList(username: String,password:String,listId:Int,text:String,lastUpdated: LocalDateTime) : Boolean
}