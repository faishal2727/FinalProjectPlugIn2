package com.example.finalapps

import android.content.Context
import android.content.SharedPreferences
import android.graphics.EmbossMaskFilter
import android.provider.ContactsContract
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
        fun ApiPoint():ApiPoint = getClient().create(ApiPoint::class.java)
    }

}
class Constant {
    companion object {
        const val BASE_URL = "https://apibarang.herokuapp.com/"
        fun SetToken(context : Context, Token : String){
            val  TakeToken = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            TakeToken.edit().apply{
                putString("Token",Token)
                apply()
            }
        }
        fun GetToken(context : Context):String{
            val sharepref = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val token = sharepref.getString("Token","Undefined")
            return token!!
        }
        fun DeletToken (context: Context){
            val deletetoken = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            deletetoken.edit().clear().apply()
        }
        fun SetNama(context: Context,Nama :String){
            val nama = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            nama.edit().apply {
                putString("Name",Nama)
                apply()
            }
        }
        fun GetNama(context: Context):String?{
            val name = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val nameregist = name.getString("Name","NAMA")
            return nameregist
        }
        fun ClearNama(context: Context){
            val delete = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }
        fun SetUsername(context: Context,Username :String){
            val username = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            username.edit().apply {
                putString("Username",Username)
                apply()
            }
        }
        fun GetUsername(context: Context):String?{
            val name = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val nameregist = name.getString("Username","user")
            return nameregist
        }
        fun ClearUsername(context: Context){
            val delete = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }
        fun SetEmail(context: Context,email :String){
            val Email = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            Email.edit().apply {
                putString("Email",email)
                apply()
            }
        }
        fun GetEmail(context: Context):String?{
            val email = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val emailregist = email.getString("Email","email")
            return emailregist
        }
//        fun SetPassword(context: Context,password : String){
//            val Password = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
//            Password.edit().apply {
//                putString("Pass",password)
//                apply()
//            }
//        }
//        fun GetPassword(context: Context):String?{
//            val password = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
//            val passregist = password.getString("Pass","pass")
//            return passregist
//        }

    }
}