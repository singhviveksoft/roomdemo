package com.example.userdemo.`interface`

import com.example.userdemo.db.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("movielist.json")
    suspend fun getMovie():List<MovieModel>

    companion object{
        const val BASE_URL="https://howtodoandroid.com/"

         var retrofitService:Api?=null

        fun getInstance():Api{
            if (retrofitService==null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                retrofitService=retrofit.create(Api::class.java)
            }
             return retrofitService!!
        }
    }
}