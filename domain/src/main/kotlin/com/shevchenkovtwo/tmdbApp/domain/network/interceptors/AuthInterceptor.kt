package com.shevchenkovtwo.tmdbApp.domain.network.interceptors

import com.shevchenkovtwo.tmdbApp.domain.network.utils.NetworkConfig.apiKey
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter("api_key", apiKey).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

}
