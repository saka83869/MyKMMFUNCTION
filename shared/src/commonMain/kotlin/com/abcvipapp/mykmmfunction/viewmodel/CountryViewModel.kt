package com.abcvipapp.mykmmfunction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcvipapp.mykmmfunction.data.Country
import com.abcvipapp.mykmmfunction.data.CountryGit
import com.abcvipapp.mykmmfunction.data.CountryVercel
import com.abcvipapp.mykmmfunction.data.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _objects: MutableStateFlow<List<Country>?> = MutableStateFlow(null)
    val objects: StateFlow<List<Country>?> = _objects

    private val _objectsGit: MutableStateFlow<List<CountryGit>?> = MutableStateFlow(null)
    val objectsGit: StateFlow<List<CountryGit>?> = _objectsGit

    private val _objectsVercel: MutableStateFlow<List<CountryVercel>?> = MutableStateFlow(null)
    val objectsVercel: StateFlow<List<CountryVercel>?> = _objectsVercel

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> = _error

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _loadingGit: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loadingGit: StateFlow<Boolean> = _loadingGit

    private val _loadingVercel: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loadingVercel: StateFlow<Boolean> = _loadingVercel

    fun fetch() {
        println("CountryViewModel start call")
        viewModelScope.launch {
            val data = repository.fetch()
            data?.let {
                _loading.value = false
                _objects.value = it
            } ?: run {
                _loading.value = false
                _error.value = "ERROR"
            }
        }
    }

    fun fetchGit() {
        println("CountryViewModel start call git")
        viewModelScope.launch {
            val data = repository.fetchGit()
            data?.let {
                _loadingGit.value = false
                _objectsGit.value = it
            } ?: run {
                _loadingGit.value = false
                _error.value = "ERROR"
            }
        }
    }

    fun fetchVercel() {
        println("CountryViewModel start call git")
        viewModelScope.launch {
            val data = repository.fetchVercel()
            data?.let {
                _loadingVercel.value = false
                _objectsVercel.value = it
            } ?: run {
                _loadingVercel.value = false
                _error.value = "ERROR"
            }
        }
    }
}
