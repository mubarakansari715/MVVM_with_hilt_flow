package com.mubarak.myapplicationfortest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.myapplicationfortest.repository.MainRepository
import com.mubarak.myapplicationfortest.utils.ApiStateForList
import com.mubarak.myapplicationfortest.model.HomeDataClass
import com.mubarak.myapplicationfortest.utils.ApiState
import com.mubarak.myapplicationtest.model.HomeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(val repository: MainRepository) : ViewModel() {

    private val _mStatus = MutableStateFlow<ApiStateForList<HomeDataClass>>(ApiStateForList.OnLoading)
    val mHomePageResponse: StateFlow<ApiStateForList<HomeDataClass>> = _mStatus

    fun getHomePageCallApi() = viewModelScope.launch {
        _mStatus.value = ApiStateForList.OnLoading
        _mStatus.value = ApiStateForList.OnSuccessResponse(repository.getHomePageData())
    }



    private val _mRespose = MutableStateFlow<ApiState<HomeResponse>>(ApiState.OnEmpty)
    val responseData: StateFlow<ApiState<HomeResponse>> = _mRespose


    fun getResponseData() = viewModelScope.launch {
        _mRespose.value = ApiState.OnLoading
        _mRespose.value = ApiState.OnSuccessResponse(repository.getHomeData())

    }


}