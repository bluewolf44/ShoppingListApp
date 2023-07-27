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

sealed interface MarsUiState {
    data class Success(val person: Person) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}


class LoginInViewModel constructor(): ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos(username:String,password:String) {

        viewModelScope.launch {
//            val c = Customer("125","Daniel3","West","DanielWest618@gmail.com")
//
//            try {
//                val listResult = MarsApi.retrofitService.postRequest(c).enqueue(object: Callback<Customer> {
//                    override fun onFailure(call: Call<Customer>, t: Throwable) {
//                        Log.i("fail", "fail")
//                    }
//
//                    override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
//                        if(response.isSuccessful) {
//                            Log.i("work", "work")
//                        } else {
//                            Log.i("maybe", "maybe")
//                        }
//                    }
//                })
//            }catch (e: IOException) {
//                Log.i("test2", e.toString())
//            }


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
