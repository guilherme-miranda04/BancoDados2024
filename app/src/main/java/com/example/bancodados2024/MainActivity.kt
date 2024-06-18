package com.example.bancodados2024

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bancodados2024.bd.AppDatabase
import com.example.bancodados2024.ui.theme.BancoDados2024Theme
import com.example.bancodados2024.viewmodels.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BancoDados2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApplication()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApplication(){
    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)
    val productViewModel: ProductViewModel = viewModel()
    val state = productViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Room Database")
            })
        }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxWidth().padding(16.dp)) {
            OutlinedTextField(value = state.value.name, onValueChange = { productViewModel.updateName(it) }, label = { Text(text = "Name")})
            OutlinedTextField(value = state.value.description, onValueChange = { productViewModel.updateDescription(it) }, label = { Text(text = "Description")})
            OutlinedTextField(value = state.value.price.toString(), onValueChange = { productViewModel.updatePrice( it.toDouble())}, label = { Text(text = "Price")})
            Button(onClick = {
                productViewModel.save()
                Toast.makeText(ctx,
                    "Product saved",
                    Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BancoDados2024Theme {
        Greeting("Android")
    }
}