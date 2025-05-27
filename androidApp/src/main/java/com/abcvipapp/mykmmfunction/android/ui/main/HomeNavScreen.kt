package com.abcvipapp.mykmmfunction.android.ui.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.abcvipapp.mykmmfunction.android.LoadingOverlay
import com.abcvipapp.mykmmfunction.android.ex.baseKoinViewModel
import com.abcvipapp.mykmmfunction.data.Country
import com.abcvipapp.mykmmfunction.viewmodel.CountryViewModel

@Composable
fun HomeNavScreen(characterSelected: ((String) -> Unit)? = null) {
    val viewModel = baseKoinViewModel<CountryViewModel>()
    val objects by viewModel.objects.collectAsState()
    val loading by viewModel.loading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LoadingOverlay(loading = loading)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = objects?.size ?: 0) { index ->
                val character = objects?.get(index)
                character?.let {
                    CharactersListRowView(character, characterSelected)
                }
            }
        }
    }
}

@Composable
fun CharactersListRowView(country: Country, characterSelected: ((String) -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                characterSelected?.invoke(
                    country.flags?.png ?: country.flags?.svg.toString()
                )
            })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = country.flags?.png ?: country.flags?.svg,
            contentDescription = country.name?.common,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(country.name?.common ?: "")
        }
    }
    Divider()
}
