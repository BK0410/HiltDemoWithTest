package com.bk.hiltdemo.hiltwithretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// MyViewModel.kt
@HiltViewModel
class DogViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _data = MutableLiveData<ApiResponse>()
    val data: LiveData<ApiResponse> get() = _data

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch() {
            try {
                val response = apiService.fetchData()
                _data.value = response
                println(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
