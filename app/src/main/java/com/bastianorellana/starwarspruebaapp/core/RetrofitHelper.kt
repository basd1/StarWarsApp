package com.bastianorellana.starwarspruebaapp.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    fun getRetrofit():Retrofit{

        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        client.connectTimeout(15, TimeUnit.SECONDS)
        client.readTimeout(15, TimeUnit.SECONDS)
        client.writeTimeout(15, TimeUnit.SECONDS)

        return Retrofit
            .Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}