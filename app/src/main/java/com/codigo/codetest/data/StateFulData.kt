package com.codigo.codetest.data

sealed class StateFulData<out T : Any> {
    data class Success<T : Any>(val result : T) : StateFulData<T>()
    data class Error(val msg : String) : StateFulData<Nothing>()
    object Loading : StateFulData<Nothing>()
}
