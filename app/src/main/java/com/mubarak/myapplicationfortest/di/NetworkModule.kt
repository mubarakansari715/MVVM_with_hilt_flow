package com.mubarak.myapplicationfortest.di

import android.content.Context
import com.mubarak.myapplicationfortest.network.ApiInterface
import com.mubarak.myapplicationfortest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    /***
     * Retrofit calling api
     */
    @Provides
    fun providesApiObj(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

}