package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalapps.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonSignUp()
        ButtonBack()
    }
    private fun Register() {
        val name = binding.etDaftarNama.text.toString()
        val username = binding.etDafatrUsername.text.toString()
        val email = binding.etDaftarEmail.text.toString()
        val password = binding.etDaftarPassword.text.toString()
        if (binding.etDaftarNama.text.isNullOrEmpty() && binding.etDafatrUsername.text.isNullOrEmpty() && binding.etDaftarEmail.text.isNullOrEmpty() && binding.etDaftarPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Anda Belum Mengisi dengan Benar", Toast.LENGTH_SHORT).show()
        } else {
            ApiService.ApiPoint().signUp(name, username, email, password)
                    .enqueue(object : Callback<SingleRespon<DataResponse>> {
                        override fun onResponse(call: Call<SingleRespon<DataResponse>>, response: Response<SingleRespon<DataResponse>>) {
                            if (response.isSuccessful) {
                                val body = response.body()
                                if (body != null) {
                                    Toast.makeText(applicationContext,body.msg, Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                val errorBody = response.errorBody().toString()
                                val code = response.code()
                                Log.e("Eror", errorBody)
                                Toast.makeText(applicationContext, code.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<SingleRespon<DataResponse>>, t: Throwable) {
                            println(t.message)
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
        }
    }
    private fun ButtonSignUp() {
        binding.btnSignUP.setOnClickListener {
            if (binding.etDaftarNama.text.isNullOrEmpty() && binding.etDafatrUsername.text.isNullOrEmpty() && binding.etDaftarEmail.text.isNullOrEmpty() && binding.etDaftarPassword.text.isNullOrEmpty()) {
                Toast.makeText(this, "Anda Belum Mengisi dengan Benar ", Toast.LENGTH_SHORT).show()
            } else {
            Register()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    }
    private fun ButtonBack(){
        binding.btnBack.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
        }
    }
}