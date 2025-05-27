package com.abcvipapp.mykmmfunction.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel: ViewModel() {

    private val _selectedImageUrl = MutableStateFlow<String?>(null)
    val selectedImageUrl = _selectedImageUrl.asStateFlow()

    fun setImageUrl(url: String) {
        _selectedImageUrl.value = url
    }

}
