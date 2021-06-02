package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalapps.databinding.ActivityTampungDataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityTampungData : AppCompatActivity() {
    private lateinit var binding: ActivityTampungDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampungDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataForm()
        ButtonClear()
        ButtonKembali()
    }
    private fun getDataForm(){
        ApiService.ApiPoint().getBarangId(intent.getIntExtra("id",1))
                .enqueue(object : Callback<ListResponse<personRespon>> {
                    override fun onFailure(call: Call<ListResponse<personRespon>>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<ListResponse<personRespon>>, response: Response<ListResponse<personRespon>>) {
                        if (response.isSuccessful){
                            val body = response.body()
                            if (body != null){
                                show(body.data)
                                Log.d("get", "${body.data}")
                            }
                        }
                    }
                })
    }
    private fun show(post : List<personRespon>){
        binding.textNama.text = "Nama : "+post[0].nama
        binding.textKode.text = "Kode   : "+post[0].kode.toString()
        binding.textCreated.text = "Created : "+post[0].createdAt
        binding.textUpdated.text = "Updated : "+post[0].updatedAt
    }
    private fun deleteData(){
        ApiService.ApiPoint().delete(intent.getIntExtra("id",1))
                .enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println(t.message)
                    }
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext, "Data Sukses Dihapus", Toast.LENGTH_LONG).show()
                        }
                    }
                })
    }
    private fun ButtonClear(){
        binding.btnClear.setOnClickListener {
            deleteData()
            finish()
        }
    }
    private fun ButtonKembali(){
        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this,ActivityAdd::class.java))
            finish()
        }
    }
}