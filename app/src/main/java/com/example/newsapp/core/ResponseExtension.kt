package com.example.newsapp.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

object ResponseExtension {

    /**
     * Обрабатывает [retrofit2.Response] и возвращает ResultWrapper
     */
    fun <T : Any> Response<T>.handleResponse(): ResultWrapper<T> {
        return try {
            if (isSuccessful) {
                return this.body()?.let { body ->
                    ResultWrapper.Success(body)
                } ?: ResultWrapper.Error(
                    error = "Empty Body received"
                )
            } else {
                ResultWrapper.Error("Something went wrong...")
            }
        } catch (exception: Exception) {
            ResultWrapper.Error(
                error = "$exception\n"
            )
        }
    }

    suspend fun <T : Any> returnSafely(
        scope: CoroutineDispatcher = Dispatchers.IO,
        content: suspend CoroutineDispatcher.() -> ResultWrapper<T>
    ): ResultWrapper<T> {
        return try {
            content.invoke(scope)
        } catch (exception: Exception) {
            ResultWrapper.Error(error = exception.message.toString())
        }
    }
}