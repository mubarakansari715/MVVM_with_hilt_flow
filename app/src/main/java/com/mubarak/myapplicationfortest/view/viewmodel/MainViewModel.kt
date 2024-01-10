package com.mubarak.myapplicationfortest.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.myapplicationfortest.view.repository.MainRepository
import com.mubarak.myapplicationfortest.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(val repository: MainRepository) : ViewModel() {

    private val _mStatus = MutableStateFlow<ApiState>(ApiState.Loading)
    val mHomePageResponse: StateFlow<ApiState> = _mStatus

    fun getHomePageCallApi() = viewModelScope.launch {
        _mStatus.value = ApiState.Loading
        _mStatus.value = ApiState.Success(repository.getHomePageData())
    }
}