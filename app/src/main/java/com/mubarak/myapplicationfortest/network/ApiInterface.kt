package com.mubarak.myapplicationfortest.network

import com.mubarak.myapplicationfortest.model.HomeDataClass
import retrofit2.http.GET
import javax.inject.Singleton

interface ApiInterface {


    @GET("photos")
    suspend fun getPostData(): List<HomeDataClass>
}