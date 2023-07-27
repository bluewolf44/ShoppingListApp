package com.example.shoppinhlistapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinhlistapp.network.Person
import com.example.shoppinhlistapp.network.ShoppingAppApi
import kotlinx.coroutines.launch

 sealed interface MarsUiState {
    data class Success(val person: Person) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}


class SignUpViewModel: ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
    fun addPerson(person:Person) {

        viewModelScope.launch {

            marsUiState = try {
                ShoppingAppApi.retrofitService.addPerson(person)
                MarsUiState.Success(
                    person
                )
            }catch (e: Exception) {
                MarsUiState.Error
            }
        }
    }
}
