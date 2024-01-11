package com.mubarak.myapplicationfortest.view.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mubarak.myapplicationfortest.R
import com.mubarak.myapplicationfortest.utils.ApiState
import com.mubarak.myapplicationfortest.view.model.HomeDataClass
import com.mubarak.myapplicationfortest.view.viewmodel.MainViewModel
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
                    is ApiState.OnLoading -> {
                        Log.e("TAG", "onCreate: onLoading")
                    }

                    is ApiState.OnEmpty -> {
                        // DebugLog.e("@@@ OnEmpty")
                    }

                    is ApiState.OnSuccessResponse<HomeDataClass> -> {
                        // DebugLog.e("@@@ Success ${it.data}")
                        // binding.txtShowResponse.text = it.data.toString()
                        Log.e("TAG", "onCreate: mubarak :: ${it.response}")
                    }

                    is ApiState.OnFailed -> {
                        Log.e("TAG", "onCreate: onFailed ${it.message}")
                    }

                }
            }
        }

    }
}