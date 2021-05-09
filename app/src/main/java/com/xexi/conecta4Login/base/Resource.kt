package com.xexi.conecta4Login.base


// clase sellada
sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
    data class Error<out T>(val error: String): Resource<T>()
}