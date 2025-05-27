package com.abcvipapp.mykmmfunction.android.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.abcvipapp.mykmmfunction.data.CountryVercel
import com.abcvipapp.mykmmfunction.viewmodel.CountryViewModel

@Composable
fun MoreNavScreen(characterSelected: ((String) -> Unit)? = null) {
    val viewModel = baseKoinViewModel<CountryViewModel>()
    val objects by viewModel.objectsVercel.collectAsState()
    val loading by viewModel.loadingVercel.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LoadingOverlay(loading = loading)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = objects?.size ?: 0
            ) { index ->
                val character = objects?.get(index)
                character?.let {
                    MoreListRowView(character, characterSelected)
                }
            }
        }
    }
}

@Composable
fun MoreListRowView(country: CountryVercel, characterSelected: ((String) -> Unit)? = null) {
    val url = "https://country-code-au6g.vercel.app/" + country.image
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                characterSelected?.invoke(url)
            })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = url,
            contentDescription = country.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(country.name ?: "")
        }
    }
    Divider()
}
