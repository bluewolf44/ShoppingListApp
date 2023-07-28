package com.example.shoppinhlistapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinhlistapp.network.ListClass
import com.example.shoppinhlistapp.network.Person
import com.example.shoppinhlistapp.network.ShoppingAppApi
import kotlinx.coroutines.launch

sealed interface ListsState {
    data class Success(val lists: List<ListClass>) : ListsState
    object Error : ListsState
    object Loading : ListsState
}

sealed interface MarsUiState {
    data class Success(val person: Person) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}


class SharedViewModel : ViewModel()  {
    var listsState: ListsState by mutableStateOf(ListsState.Loading)
        private set

    var LoginState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    var SignupState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    var person by mutableStateOf<Person>(Person("","","","",""))
        private set

    fun setCurrentPerson(newPerson:Person)
    {
        person = newPerson
    }

    fun getLists(username:String,password:String) {

        viewModelScope.launch {
            listsState = try {
                var lists = ShoppingAppApi.retrofitService.getList(username,password)
                ListsState.Success(
                    lists
                )
            } catch (e: Exception) {
                ListsState.Error
            }
        }
    }

    fun addPerson(person:Person) {

        viewModelScope.launch {

            SignupState = try {
                ShoppingAppApi.retrofitService.addPerson(person)
                MarsUiState.Success(
                    person
                )
            }catch (e: Exception) {
                MarsUiState.Error
            }
        }
    }

    fun getPerson(username:String,password:String) {

        viewModelScope.launch {
            LoginState = try {
                val person = ShoppingAppApi.retrofitService.getPerson(username,password)

                MarsUiState.Success(
                    person
                )

            } catch (e: Exception) {
                MarsUiState.Error
            }
        }
    }
}