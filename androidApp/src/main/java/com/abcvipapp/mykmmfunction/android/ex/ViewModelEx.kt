package com.abcvipapp.mykmmfunction.android.ex

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified T : ViewModel> baseKoinViewModel(): T {
    val owner = LocalContext.current as? ComponentActivity
        ?: error("LocalContext is not a ComponentActivity")

    return koinViewModel<T>(viewModelStoreOwner = owner)
}

@Composable
inline fun <reified T : ViewModel> LocalKoinViewModel(): T {
    val owner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner found in the current context")
    return koinViewModel<T>(viewModelStoreOwner = owner)
}
