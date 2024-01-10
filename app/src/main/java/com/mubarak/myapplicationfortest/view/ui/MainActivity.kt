package com.mubarak.myapplicationfortest.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mubarak.myapplicationfortest.R
import com.mubarak.myapplicationfortest.utils.ApiState
import com.mubarak.myapplicationfortest.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val homeViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        homeViewModel.getHomePageCallApi()

        lifecycleScope.launch {
            homeViewModel.mHomePageResponse.collectLatest {

                when (it) {
                    is ApiState.Loading -> {
                        //DebugLog.e("@@@ Loading")
                    }
                    is ApiState.Empty -> {
                       // DebugLog.e("@@@ Empty")
                    }
                    is ApiState.Success<*> -> {
                       // DebugLog.e("@@@ Success ${it.data}")
                       // binding.txtShowResponse.text = it.data.toString()
                        Log.e("TAG", "onCreate: mubarak :: ${it.data}", )
                    }
                    else -> {
                       // DebugLog.e("@@@ Error: $it")
                    }

                }
            }
        }

    }
}