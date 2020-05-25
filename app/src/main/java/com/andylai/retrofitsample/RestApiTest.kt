package com.andylai.retrofitsample

import android.content.Context
import android.util.Log
import com.andylai.retrofitsample.dataclass.EnrollResponseBody
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiTest(private val mContext: Context) {
    val TAG = "Andy"

    @TestOnly
    fun testPost() {
//		val macAddress = DataRepository.getInstance(mContext).macAddress
        val macAddress = "70:2e:b9:f8:b8:a2"
        Log.d(TAG, "macAddress = $macAddress")

        CoroutineScope(Dispatchers.IO).launch {
            val result = RestApiManager.getCode(macAddress)
            if (result is Result.Success) {
                Log.d("Andy", "result success")
                val data = result.data
                Log.d("Andy", "data = ${data?.code}")
                Log.d("Andy", "data = ${data?.instance_id}")
                Log.d("Andy", "data = ${data?.mqtt_host}")
            } else {
                Log.d("Andy", "result fail")
            }
        }
    }

    @TestOnly
    fun testGet() {
//		val instanceId = DataRepository.getInstance(mContext).instanceId
        val instanceId = "39f5c985-cc11-4484-b3af-ea1d5a67c59a"
        if (instanceId != null) {
            Log.d(TAG, "instanceId = $instanceId")
            RestApiManager.checkInstanceIdExist(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d(TAG, "response.isSuccessful() = " + response.isSuccessful)
                    Log.d(TAG, "exist = " + response.body()?.get("exist")?.asBoolean)
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {}
            }, instanceId)
        }
    }
}