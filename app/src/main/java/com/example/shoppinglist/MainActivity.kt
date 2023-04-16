package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainApp()
                }
            }
        }
    }
}
@Composable
fun ButtonTest()
{
    var amountInput by remember {
        mutableStateOf("")
    }
    var tipInput by remember {
        mutableStateOf("")
    }

    val amount = calculateTip(amountInput.toDoubleOrNull() ?: 0.0,tipInput.toDoubleOrNull() ?: 0.0)
    Column() {
        Text(text = "Tip")
        Spacer(modifier = Modifier.height(16.dp))
        EditNUmberField("GOD give a number",amountInput) { amountInput = it }
        Spacer(modifier = Modifier.height(16.dp))
        EditNUmberField("tip%",tipInput) { tipInput = it }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total amount:$amount")
    }
}
private fun calculateTip(n:Double,tip:Double=15.0):String{
    val amount:Double = n*tip/100f + n
    return "$amount"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNUmberField(n:String,value:String,onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        label = { Text(text = n)}
    )
}
@Composable
fun MainApp()
{
    ButtonTest()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        MainApp()
    }
}