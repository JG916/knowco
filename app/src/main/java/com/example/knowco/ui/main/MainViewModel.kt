package com.example.knowco.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowco.data.source.remote.CoinRankService
import com.example.knowco.data.source.remote.ServiceBuilder
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val coinRankService = ServiceBuilder.createService(CoinRankService::class.java)
    
    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val coin = coinRankService.getCoin(1)
            _isLoading.value = false
        }
    }
}
