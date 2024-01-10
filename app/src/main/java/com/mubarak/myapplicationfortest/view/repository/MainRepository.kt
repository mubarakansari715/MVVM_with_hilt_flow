package com.mubarak.myapplicationfortest.view.repository

import com.mubarak.myapplicationfortest.view.model.HomeDataClass
import com.mubarak.myapplicationfortest.network.ApiInterface
import javax.inject.Inject

class MainRepository@Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getHomePageData(): List<HomeDataClass> {
        return apiInterface.getPostData()
    }
}