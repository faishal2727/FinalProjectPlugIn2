package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapps.databinding.ActivityAddBinding
import kotlinx.android.synthetic.main.activity_bottom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityAdd : AppCompatActivity() {
    private lateinit var adapterrrrr : Adapter
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav1()
        ButtonAdd()
    }
    override fun onResume() {
        super.onResume()
        getBarang()
    }
    fun getBarang(){
        ApiService.ApiPoint().getBarang()
                .enqueue(object: Callback<ListResponse<personRespon>>{
                    override fun onFailure(call: Call<ListResponse<personRespon>>, t: Throwable) {
                        println(t.message)
                    }
                    override fun onResponse(
                            call: Call<ListResponse<personRespon>>,
                            response: Response<ListResponse<personRespon>>
                    ) {
                        if(response.isSuccessful){
                            val body = response.body()
                            if(body != null){
                                RV(body.data)
                                Toast.makeText(applicationContext,"Berhasil Get Data", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
    }
    fun RV(Person : List<personRespon>){
        adapterrrrr = Adapter(Person,object : Adapter.Click1{
            override fun click1(post: personRespon) {
                startActivity(Intent(this@ActivityAdd,ActivityTampungData::class.java).apply {
                putExtra("id",post.id)
            })
            }
            override fun Edit1(post: personRespon) {
                startActivity(Intent(this@ActivityAdd,EdiyActivity::class.java).apply {
                    putExtra("id",post.id)
                    putExtra("nama",post.nama)
                    putExtra("kode",post.kode.toString())
                })
            }
        })
        binding.rcy.apply{
            adapter = adapterrrrr
            layoutManager = LinearLayoutManager(this@ActivityAdd)
        }
    }
    private fun bottomNav1(){
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id. Add -> startActivity(Intent(this, ActivityAdd::class.java).apply { finish() })
                R.id. Profil-> startActivity(Intent(this, Bottom::class.java).apply { finish() })
            }
            finish()
            true
        }
    }
    private fun ButtonAdd(){
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, ActivityAdd2::class.java))
            finish()
        }
    }
}






