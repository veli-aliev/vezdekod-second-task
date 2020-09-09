package com.travels.searchtravels.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

public interface ServerApi {
    public companion object Factory {
        fun create(): ServerApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://instarlike.com/api/")
                .build()

            return retrofit.create<ServerApi>(ServerApi::class.java!!)
        }
    }


//    @FormUrlEncoded
//    @POST("searchTag")
//    fun searchTag(@Field("tag") tag: String): Observable<TagsResponse>


}