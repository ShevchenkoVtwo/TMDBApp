package com.shevchenkovtwo.homework.network.datasources

import com.shevchenkovtwo.homework.network.utils.DataState
import retrofit2.Response


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataState<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return DataState.Success(body)
            }
            return DataState.Loading
        } catch (e: Exception) {
            return DataState.Error(e)
        }
    }
}

