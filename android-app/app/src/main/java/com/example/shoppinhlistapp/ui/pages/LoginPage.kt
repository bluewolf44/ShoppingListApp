package com.example.shoppinhlistapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.ui.Screen
import com.example.shoppinhlistapp.ui.viewmodel.LoginInViewModel
import com.example.shoppinhlistapp.ui.viewmodel.MarsUiState

@Composable
fun LoginPage(navController: NavHostController) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var errorText by remember {
        mutableStateOf("")
    }

    val shoppingViewModel: LoginInViewModel = viewModel()

    when (shoppingViewModel.marsUiState) {
        is MarsUiState.Loading -> {}
        is MarsUiState.Success -> {
            navController.navigate(route = Screen.ListScreen.route)
            shoppingViewModel.marsUiState = MarsUiState.Loading
        }

        is MarsUiState.Error -> {
            errorText = "Password or UserName is wrong"
            shoppingViewModel.marsUiState = MarsUiState.Loading
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to Daniels Shopping app",Modifier.padding(20.dp))
        EditUserField("Enter UserName", username) { username = it }
        EditPasswordField("Enter password", password) { password = it }
        Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth())
        {
            Button(
                onClick = {
                    if(username == "" || password == "")
                    {
                        errorText = "Please enter Password and UserName"
                        return@Button
                    }
                    shoppingViewModel.getMarsPhotos(username,password)
                },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "Sign in")
            }

            Button(
                onClick = { navController.navigate(route = Screen.SignUp.route)},
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "Sign up")
            }
        }
        Text(text = errorText,modifier = Modifier.padding(5.dp),)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserField(n:String, value:String, onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPasswordField(n:String, value:String, onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
    )
}
