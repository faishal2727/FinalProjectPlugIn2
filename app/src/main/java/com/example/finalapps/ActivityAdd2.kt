package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalapps.databinding.ActivityAdd2Binding
import retrofit2.Call
import retrofit2.Response

class ActivityAdd2 : AppCompatActivity() {
    private lateinit var binding: ActivityAdd2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdd2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonSimpan()
        ButtonKembali()
        Peringatan()
    }
    private fun postData(){
        var nama = binding.etNama.text.toString()
        var kode = binding.etKode.text.toString()
        ApiService.ApiPoint().PostData(nama,kode.toInt())
                .enqueue(object : retrofit2.Callback<SingleRespon<personRespon>>{
                    override fun onFailure(call: Call<SingleRespon<personRespon>>, t: Throwable) {
                        println(t.message)
                    }
                    override fun onResponse(
                            call: Call<SingleRespon<personRespon>>, response: Response<SingleRespon<personRespon>>
                    ) {
                        if (response.isSuccessful){
                            val body  = response.body()
                            Toast.makeText(applicationContext, " Sukses Tambah Data", Toast.LENGTH_LONG).show()
                            println("sukses tambah data"+ body)
                        }
                    }
                })
    }
    private fun ButtonSimpan(){
        binding.btnSave.setOnClickListener {
            startActivity(Intent(this,ActivityAdd::class.java))
            postData()
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