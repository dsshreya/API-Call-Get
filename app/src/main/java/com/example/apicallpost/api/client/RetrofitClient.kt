package com.example.apicallpost.api.client

import com.example.apicallpost.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient  {
    companion object {
         private var retrofit: Retrofit? = null
//         private const val BASE_URL = "https://jsonplaceholder.typicode.com"
         fun getRetrofitInstance(): Retrofit? {
             if (retrofit == null) {
                 retrofit = Retrofit.Builder()
                     .baseUrl(Constants.BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
             }
             return retrofit
         }
    }
}