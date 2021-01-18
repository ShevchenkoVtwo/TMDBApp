package com.shevchenkovtwo.homework.network.api

import com.shevchenkovtwo.homework.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
             val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
        }

        val api: TMDBApi by lazy {
            retrofit.create(TMDBApi::class.java)
        }
    }
}