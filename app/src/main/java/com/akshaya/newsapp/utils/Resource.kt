package com.akshaya.newsapp.utils

sealed class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    class success<T>(data: T?) : Resource<T>(Status.SUCCESS, data, null)
    class error<T>(msg: String) : Resource<T>(Status.ERROR, null, msg)
    class loading<T>() : Resource<T>(Status.LOADING, null, null)

}