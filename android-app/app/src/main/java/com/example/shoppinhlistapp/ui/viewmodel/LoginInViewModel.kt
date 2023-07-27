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
import java.io.IOException


class LoginInViewModel: ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
    fun getPerson(username:String,password:String) {

        viewModelScope.launch {
            marsUiState = try {
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
