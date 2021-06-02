package com.example.finalapps

import retrofit2.Call
import retrofit2.http.*

interface ApiPoint {
    @GET("barang/")
    fun getBarang(): Call<ListResponse<personRespon>>

    @GET("barang/{id}")
    fun getBarangId(@Path("id")id:Int):Call<ListResponse<personRespon>>

    @DELETE("barang/{id}")
    fun delete(@Path("id")id: Int): Call<Void>

    @FormUrlEncoded
    @POST("barang/")
    fun PostData
            (@Field("nama") nama: String,
             @Field("kode")kode : Int): Call<SingleRespon<personRespon>>

    @FormUrlEncoded
    @PUT("barang/{id}")
    fun UpdateData(@Path("id")id:Int,
                     @Field("nama")nama : String,
                     @Field("kode")kode :Int) : Call<SingleRespon<personRespon>>
    @FormUrlEncoded
    @POST("auth/sign-up")
    fun signUp
                (@Field("name")name : String,
                 @Field("username")username : String,
                 @Field("email")email :String,
                 @Field("password")password : String) : Call<SingleRespon<DataResponse>>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun SignIn(@Field("username")username : String,
               @Field("password")password : String) : Call<SingleRespon<DataResponse>>
}

