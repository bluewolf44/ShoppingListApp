package com.shoppinglist.dao

import com.shoppinglist.model.*

interface ListDAO {
    suspend fun allList(): List<ListClass>
    suspend fun getListForPerson(username: String,password:String): List<ListClass>
    suspend fun getList (username: String,password:String,listId:Int): ListClass?
    suspend fun addList(list:ListClass): ListClass?
    suspend fun deleteList(username: String,password:String,listId:Int) : Boolean
}