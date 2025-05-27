package com.abcvipapp.mykmmfunction.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Main : Screen("main", "Main", Icons.Default.List)
    object Setting : Screen("setting", "Setting", Icons.Default.Settings)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object More : Screen("more", "More", Icons.Default.MoreVert)
}
