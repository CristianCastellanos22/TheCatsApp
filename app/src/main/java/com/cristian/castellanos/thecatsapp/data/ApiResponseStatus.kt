package com.cristian.castellanos.thecatsapp.data

sealed class ApiResponseStatus<T>() {
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Loading<T>: ApiResponseStatus<T>()
    class Error<T>(val messageId: String): ApiResponseStatus<T>()
}