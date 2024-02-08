package com.mubarak.myapplicationfortest.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mubarak.myapplicationfortest.R
import com.mubarak.myapplicationfortest.utils.ApiStateForList
import com.mubarak.myapplicationfortest.model.HomeDataClass
import com.mubarak.myapplicationfortest.utils.ApiState
import com.mubarak.myapplicationfortest.viewmodel.MainViewModel
import com.mubarak.myapplicationtest.model.HomeResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val homeViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        homeViewModel.getHomePageCallApi()

        lifecycleScope.launch {
            homeViewModel.mHomePageResponse.collect {

                when (it) {
                    is ApiStateForList.OnLoading -> {
                        Log.e("TAG", "onCreate: onLoading")
                    }

                    is ApiStateForList.OnEmpty -> {
                        // DebugLog.e("@@@ OnEmpty")
                    }

                    is ApiStateForList.OnSuccessResponse<HomeDataClass> -> {
                        // DebugLog.e("@@@ Success ${it.data}")
                        // binding.txtShowResponse.text = it.data.toString()
                        Log.e("TAG", "onCreate: mubarak :: ${it.response}")
                    }

                    is ApiStateForList.OnFailed -> {
                        Log.e("TAG", "onCreate: onFailed ${it.message}")
                    }

                }
            }
        }


        ////////////////////////////////

        homeViewModel.getResponseData()

        lifecycleScope.launch {

            homeViewModel.responseData.collect {
                when (it) {
                    is ApiState.OnEmpty -> {

                    }

                    is ApiState.OnLoading -> {

                    }

                    is ApiState.OnSuccessResponse<HomeResponse> -> {
                        Log.e("TAG", "onCreate: mubarak :: ${it.response}")
                    }

                    is ApiState.OnFailed -> {

                    }
                }
            }
        }

    }
}