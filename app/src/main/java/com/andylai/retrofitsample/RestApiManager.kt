package com.andylai.retrofitsample

import android.util.Log
import com.google.gson.JsonObject
import com.andylai.retrofitsample.dataclass.EnrollBody
import com.andylai.retrofitsample.dataclass.EnrollResponseBody
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException


object RestApiManager {
    private val DM_URL = "https://stageapi.myviewboard.com/api/dm/v1/"
//    stageapi

    private val retrofit = Retrofit.Builder()
        .baseUrl(DM_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
    private val apiService = retrofit.create(APIService::class.java)

//    fun POST(callback: Callback<EnrollResponseBody>, mac_address: String) {
//        val call = apiService.post(mac_address)
//        call.execute()
//    }

    fun getCode(callback: Callback<JsonObject>, mac_address: String) {
        val call = apiService.post(mac_address)
        call.enqueue(callback)
    }

//    fun POST(mac_address: String) {
//        val call = apiService.post(mac_address)
//    }

    fun checkInstanceIdExist(callback: Callback<JsonObject>, instanceId: String) {
        val call = apiService.checkInstanceIdExist(instanceId)
        call.enqueue(callback)
    }

    interface APIService {
        @GET("device/{device_id}/exist")
        fun checkInstanceIdExist(@Path("device_id") instanceId: String): Call<JsonObject>

        @FormUrlEncoded
        @POST("code")
        fun post(@Field("mac_address") mac_address: String): String
    }

}