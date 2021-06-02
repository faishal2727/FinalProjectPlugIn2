package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalapps.databinding.ActivityEdiyBinding
import retrofit2.Call
import retrofit2.Response

class EdiyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEdiyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataActivity()
        BtnUpdate()
        ButtonKembali()
        Peringatan()
    }
    fun getDataActivity(){
        binding.etNama.setText(intent.getStringExtra("nama"))
        binding.etKode.setText(intent.getStringExtra("kode"))
    }
    fun UpdateData(){
        val id = intent.getIntExtra("id",1)
        val nama = binding.etNama.text.toString()
        var kodee = binding.etKode.text.toString().toInt()
        ApiService.ApiPoint().UpdateData(id,nama,kodee)
            .enqueue(object : retrofit2.Callback<SingleRespon<personRespon>>{
                override fun onFailure(call: Call<SingleRespon<personRespon>>, t: Throwable) {
                    println(t.message)
                }
                override fun onResponse(call: Call<SingleRespon<personRespon>>, response: Response<SingleRespon<personRespon>>
                ) {
                    if(response.isSuccessful){
                        val body = response
                        Toast.makeText(applicationContext,"Sukses Update Data", Toast.LENGTH_LONG).show()
                        println("Sukses Update Data"+body)
                    }
                }
            })
    }
    private fun BtnUpdate(){
        binding.btnUpdate.setOnClickListener {
            UpdateData()
            finish()
        }
    }
    private fun ButtonKembali(){
        binding.btnKembali1.setOnClickListener {
            startActivity(Intent(this,ActivityAdd::class.java))
            finish()
        }
    }
    private fun Peringatan(){
        binding.etKode.setOnClickListener {
            Toast.makeText(applicationContext, "Hanya Boleh di Isi Angka", Toast.LENGTH_LONG).show()
        }
    }
}