package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalapps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SignUp()
        ButtonLogin()
        IsLogin()
    }
    private fun ReqLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (binding.etUsername.text.isNullOrEmpty() && binding.etPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Anda Belum Mengisi Username dan Password", Toast.LENGTH_SHORT).show()
        } else {
            ApiService.ApiPoint().SignIn(username, password).enqueue(object :
                    Callback<SingleRespon<DataResponse>> {
                override fun onResponse(call: Call<SingleRespon<DataResponse>>, response: Response<SingleRespon<DataResponse>>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            Constant.SetUsername(this@MainActivity!!, body.data.username)
                            Constant.SetNama(this@MainActivity!!, body.data.name)
                            Constant.SetToken(this@MainActivity!!, body.data.token)
                            Constant.SetEmail(this@MainActivity!!, body.data.email)
                            Toast.makeText(applicationContext, " Hai Selamat Datang ${body.data.name}", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MainActivity, Bottom::class.java))
                            finish()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Anda Mungkin Salah Memasukan Username atau Password, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<SingleRespon<DataResponse>>, t: Throwable) {
                    println(t.message)
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun IsLogin(){
        val token = Constant.GetToken(this)
        if(!token.equals("Undefined")){
            startActivity(Intent(this,Bottom::class.java).also {
                finish()
            })
        }
    }
    private fun ButtonLogin(){
        binding.btnLogin.setOnClickListener {
            ReqLogin()
        }
    }
    private fun SignUp(){
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }
}