package com.abcvipapp.mykmmfunction.android.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abcvipapp.mykmmfunction.android.ui.Screen
import androidx.navigation.compose.composable

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.abcvipapp.mykmmfunction.viewmodel.DetailViewModel

@Composable
fun MainScreen(navControllerMain: NavHostController, detailViewModel: DetailViewModel) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Main,
        Screen.Setting,
        Screen.Profile,
        Screen.More
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = currentRoute(navController)
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeNavScreen {
//                    val encodedUrl = URLEncoder.encode("https://country-code-au6g.vercel.app/AF.svg", "UTF-8")
//                    navControllerMain.navigate("detail/$encodedUrl")
                    detailViewModel.setImageUrl(it)
                    navControllerMain.navigate("detail")
                }
            }
            composable(Screen.Main.route) {
                HomeNavScreen {
                    detailViewModel.setImageUrl(it)
                    navControllerMain.navigate("detail")
                }
            }
            composable(Screen.Profile.route) {
                ProfileNavScreen {
                    detailViewModel.setImageUrl("https://country-code-au6g.vercel.app/AF.svg")
                    navControllerMain.navigate("detail")
                }
            }
            composable(Screen.Setting.route) {
                HomeNavScreen {
                    detailViewModel.setImageUrl(it)
                    navControllerMain.navigate("detail")
                }
            }
            composable(Screen.More.route) {
                MoreNavScreen {
                    detailViewModel.setImageUrl(it)
                    navControllerMain.navigate("detail")
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
