package com.example.shoppinhlistapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.network.Person
import com.example.shoppinhlistapp.ui.Screen
import com.example.shoppinhlistapp.ui.viewmodel.LoginInViewModel
import com.example.shoppinhlistapp.ui.viewmodel.MarsUiState
import com.example.shoppinhlistapp.ui.viewmodel.SignUpViewModel

@Composable
fun signUpPage(navController: NavHostController)
{
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var errorText by remember {
        mutableStateOf("")
    }

    val shoppingViewModel: SignUpViewModel = viewModel()

    when (shoppingViewModel.marsUiState) {
        is MarsUiState.Loading -> {}
        is MarsUiState.Success -> {
            navController.navigate(route = Screen.ListScreen.route)
            shoppingViewModel.marsUiState = MarsUiState.Loading
        }

        is MarsUiState.Error -> {
            errorText = "UserName is taken"
            shoppingViewModel.marsUiState = MarsUiState.Loading
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Please sign up",Modifier.padding(20.dp))
        EditSignField("enter username",username){ username = it }
        EditSignField("enter password",password){ password = it }
        EditSignField("enter firstname",firstname){ firstname = it }
        EditSignField("enter lastname",lastname){ lastname = it }
        EditSignField("enter email",email){ email = it }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth())
        {
            Button(
                onClick = { if(firstname == "" ||lastname == "" ||email == "" || username == "" || password == "")
                    {
                        errorText = "Please enter fields"
                        return@Button
                    }
                    shoppingViewModel.addPerson(Person(
                        firstName = firstname,
                        lastName = lastname,
                        email = email,
                        password = password,
                        userName = username
                    )) },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "Sign up")
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "Cancel")
            }
        }
        Text(text = errorText)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSignField(n:String, value:String, onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        singleLine = true,
    )
}