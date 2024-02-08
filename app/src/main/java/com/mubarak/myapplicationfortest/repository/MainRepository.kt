package com.mubarak.myapplicationfortest.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mubarak.myapplicationfortest.R
import com.mubarak.myapplicationfortest.model.HomeDataClass
import com.mubarak.myapplicationfortest.network.ApiInterface
import com.mubarak.myapplicationtest.model.HomeResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import javax.inject.Inject

class MainRepository@Inject constructor(private val apiInterface: ApiInterface, private val context: Context) {

    suspend fun getHomePageData(): List<HomeDataClass> {
        return apiInterface.getPostData()
    }

    fun getHomeData(): HomeResponse {

        val `is` = context?.resources?.openRawResource(R.raw.example_2)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } finally {
            `is`?.close()
        }

        val jsonString: String = writer.toString()


        Log.e("TAG", "onCreate: $jsonString")

        val gson = Gson().fromJson<HomeResponse>(jsonString, HomeResponse::class.java)

        return gson
    }
}