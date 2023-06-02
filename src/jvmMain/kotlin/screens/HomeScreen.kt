package screens

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navcontroller.NavController

@Composable
fun HomeScreen(
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(navController.currentScreen.value)
        Button(
            onClick = {
                navController.navigate(Screen.ProfileScreen.name)
            }
        ){
                Text("Navigate to Profile")
        }
    }
}