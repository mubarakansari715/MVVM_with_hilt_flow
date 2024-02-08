package com.mubarak.myapplicationfortest.utils

sealed class ApiStateForList<out T>() {
    object OnEmpty : ApiStateForList<Nothing>()
    object OnLoading : ApiStateForList<Nothing>()
//    class Success<T>(val data: T) : ApiStateForList<T>()
    class OnSuccessResponse<T>(val response: List<T>) : ApiStateForList<T>()
    class OnFailed(val code: Int, val message: String, val messageCode: String) :
        ApiStateForList<Nothing>()
}