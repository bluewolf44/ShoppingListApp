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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ImageTest()
                }
            }
        }
    }
}

@Composable
fun ColumnTest(vararg names: String)
{
    Column {
        for(i in names.indices) {
            val n = names[i]
            Text(text = "-Hello $n")
        }
    }
}

@Composable
fun ImageTest()
{
    val image = painterResource(id = R.drawable.blue)
    Box{
        Image(painter = image, contentDescription = null, modifier = Modifier.fillMaxSize().fillMaxWidth(), contentScale = ContentScale.Crop)
        ColumnTest("DEs","Daniel","God")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        ImageTest()
    }
}