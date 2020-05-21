package com.andylai.retrofitsample

import android.content.Context
import android.util.Log
import com.andylai.retrofitsample.dataclass.EnrollResponseBody
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly
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
//		RestApiManager.POST(object : Callback<EnrollResponseBody> {
//			override fun onResponse(call: Call<EnrollResponseBody?>, response: Response<EnrollResponseBody?>) {
//				Log.d(TAG, "response.isSuccessful() = " + response.isSuccessful)
//				if (response.body() is EnrollResponseBody) {
//					val body = response.body() as EnrollResponseBody
//					Log.d(TAG, "instance_id = " + body.instance_id)
//					Log.d(TAG, "instance_id = " + body.code)
//					Log.d(TAG, "instance_id = " + body.mqtt_host)
//
//				}
//			}
//			override fun onFailure(call: Call<EnrollResponseBody?>, t: Throwable) {
//				Log.d(TAG, "t = " + t)
//
//			}
//		}, macAddress)
		GlobalScope.launch {
			val result = RestApiManager.requestGetCode(macAddress)
//			val body = response.body() as EnrollResponseBody

			Log.d(TAG, "result = ${result.toString()}" )
//			Log.d(TAG, "instance_id = " + body.code)
//			Log.d(TAG, "instance_id = " + body.mqtt_host)
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