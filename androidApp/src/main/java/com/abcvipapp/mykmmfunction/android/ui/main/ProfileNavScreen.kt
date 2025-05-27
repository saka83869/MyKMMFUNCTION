package com.abcvipapp.mykmmfunction.android.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.abcvipapp.mykmmfunction.android.LoadingOverlay
import com.abcvipapp.mykmmfunction.android.ex.baseKoinViewModel
import com.abcvipapp.mykmmfunction.data.CountryGit
import com.abcvipapp.mykmmfunction.viewmodel.CountryViewModel

@Composable
fun ProfileNavScreen(characterSelected: ((String) -> Unit)? = null) {
    val viewModel = baseKoinViewModel<CountryViewModel>()
    val objects by viewModel.objectsGit.collectAsState()
    val loading by viewModel.loadingGit.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LoadingOverlay(loading = loading)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
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
                    ListRowView(character, characterSelected)
                }
            }
        }
    }
}

@Composable
fun ListRowView(country: CountryGit, characterSelected: ((String) -> Unit)? = null) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { characterSelected?.invoke("") })
            .padding(16.dp)
    ) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val imageRequest = ImageRequest.Builder(context)
            .data(country.flag)
            .decoderFactory(SvgDecoder.Factory())
            .build()

        val painter = rememberAsyncImagePainter(model = imageRequest, imageLoader = imageLoader)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp, 100.dp),
            contentScale = ContentScale.Fit
        )
    }
}
