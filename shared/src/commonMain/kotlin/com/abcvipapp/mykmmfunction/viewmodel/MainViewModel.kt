package com.abcvipapp.mykmmfunction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcvipapp.mykmmfunction.data.RequestParam
import com.abcvipapp.mykmmfunction.data.ResultData
import com.abcvipapp.mykmmfunction.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _objects: MutableStateFlow<ResultData?> = MutableStateFlow(null)
    val objects: StateFlow<ResultData?> = _objects

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> = _error

    fun fetch(data: RequestParam) {
        viewModelScope.launch {
            val data = repository.fetch(data)
            data?.let {
                _objects.value = it
            } ?: kotlin.run {
                _error.value = "ERROR"
            }
        }
    }
}
