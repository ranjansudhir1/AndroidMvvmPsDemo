package com.ps.psdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.psdemo.data.repository.MainRepository
import com.ps.psdemo.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val collectPostStateFlow: StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost().catch { exp ->
            postStateFlow.value = ApiState.Failure(exp)
        }.collect { data ->
            postStateFlow.value = ApiState.Success(data)
        }
    }
}