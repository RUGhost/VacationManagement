import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import screens.HomeScreen
import screens.NotificationScreen
import screens.ProfileScreen
import screens.SettingScreen


@Composable
@Preview
fun app() {

    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember { navController.currentScreen }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box (
                modifier = Modifier.fillMaxSize()
            ){
                NavigationRail(
                    modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight()
                ){
                    screens.forEach {
                        NavigationRailItem(
                            selected = currentScreen == it.name,
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.label
                                )
                            },
                            label = {
                                Text(it.label)
                            },
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.name)
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxHeight()
                ){
                    customNavigationHost(navController = navController)
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}


//Screen Holder
enum class Screen(
    val label: String,
    val icon: ImageVector
){
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    NotificationsScreen(
        label = "Notifications",
        icon = Icons.Filled.Notifications
    ),
    SettingsScreen(
        label = "Settings",
        icon = Icons.Filled.Settings
    ),
    ProfileScreen(
        label = "Profile",
        icon = Icons.Filled.Person
    ),
}


@Composable
fun customNavigationHost(
    navController: NavController
){
    NavigationHost(navController){
        composable(Screen.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(Screen.NotificationsScreen.name){
            NotificationScreen(navController)
        }
        composable(Screen.SettingsScreen.name){
            SettingScreen(navController)
        }
        composable(Screen.ProfileScreen.name){
            ProfileScreen(navController)
        }
    }.build()
}