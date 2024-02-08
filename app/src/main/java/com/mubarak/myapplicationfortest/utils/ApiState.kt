package com.mubarak.myapplicationfortest.utils

sealed class ApiState<out T>() {
    object OnEmpty : ApiState<Nothing>()
    object OnLoading : ApiState<Nothing>()

    //    class Success<T>(val data: T) : ApiState<T>()
    class OnSuccessResponse<T>(val response: T) : ApiState<T>()
    class OnFailed(val code: Int, val message: String, val messageCode: String) :
        ApiState<Nothing>()
}