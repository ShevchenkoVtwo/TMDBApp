package com.shevchenkovtwo.homework.network


sealed class ResponsesLogging<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : ResponsesLogging<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponsesLogging<T>(data, message)
    class Loading<T> : ResponsesLogging<T>()
}