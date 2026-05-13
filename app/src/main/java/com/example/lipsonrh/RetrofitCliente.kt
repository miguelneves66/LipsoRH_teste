package com.example.lipsonrh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        // IP 10.0.2.2 é usado para o emulador acessar o localhost do seu PC
        private const val BASE_URL = "http://10.0.2.2:8080/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}