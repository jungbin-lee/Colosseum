package com.h2square.colosseum.utils

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {
    companion object{

        interface  JsonResponseHandler{
            fun  onResponse(JsonObj:JSONObject)
        }
        val BASE_URL = "http://3.37.226.131"

        fun postRequestLogin(email: String, password: String,handler: JsonResponseHandler?){
            //POST /users okHttp
            val urlString= "${BASE_URL}/user"
            val formData= FormBody.Builder()
                .add("email",email)
                .add("password",password)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

            val client =OkHttpClient()
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {


                    val bodyString= response.body!!.string()
                    val jsonObj= JSONObject(bodyString)
                    Log.d("응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }

            })

            
        }

        fun putRequestSignUp(email: String, password: String,nickname: String,handler: JsonResponseHandler?){
            //POST /users okHttp
            val urlString= "${BASE_URL}/user"
            val formData= FormBody.Builder()
                .add("email",email)
                .add("password",password)
                .add("nick_name",nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

            val client =OkHttpClient()
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {


                    val bodyString= response.body!!.string()
                    val jsonObj= JSONObject(bodyString)
                    Log.d("응답 본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }

            })


        }

        fun getRequestDuplicate(type: String,value: String,handler: JsonResponseHandler?){

            val urlBuilder= "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("type",type)
            urlBuilder.addEncodedQueryParameter("value",value)

            val urlString = urlBuilder.build().toString()
            Log.d("완성된 url",urlString)
            val  request= Request.Builder()
                .url(urlString)
                .get()
                .build()

            val client= OkHttpClient()
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()

                    val jsonObject = JSONObject(bodyString)
                    Log.d("서버 응답",jsonObject.toString())

                    handler?.onResponse(jsonObject)

                }
            })
        }

    }
}