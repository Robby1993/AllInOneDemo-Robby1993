package com.app.filesender.interfaces

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


   /* @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("uploadFile")
    fun uploadFile(@Part MultipartBody.Part filePart): Call<JsonObject>*/


    @Multipart
    @POST("uploadFile")
    fun uploadFile(@Part filePart: MultipartBody.Part): Call<Any>

    @GET("getAdvertisement")
    fun getAdvertisement(@Query("adId") adId: Int): Call<Any>

    @GET("notifications")
    fun getNotifications(): Call<Any>

    @POST("submitData")
    fun submitData(@Body body: JsonObject): Call<Any>

  /*  @Headers("Content-Type: application/json")
    @POST("submitData")
    fun submitData(@Body body: String?): Call<Any>*/

}