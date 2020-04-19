package com.example.exampleproj

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofImpl {
    @GET("heroes")
    fun getHeroes(): Single<List<Hero>>

    companion object Factory {
        fun create(): RetrofImpl {//todo every time create is called it will receive a new instance. Read about singleton pattern in kotlin
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.opendota.com/api/")
                .build()

            return retrofit.create(RetrofImpl::class.java)
        }
    }

}
