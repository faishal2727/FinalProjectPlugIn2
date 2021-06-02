package com.example.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalapps.databinding.ActivityBottomBinding
import kotlinx.android.synthetic.main.activity_bottom.*

class Bottom : AppCompatActivity() {
    private lateinit var binding: ActivityBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav()
        BtnOut()
        getUser()
    }
    private fun bottomNav(){
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id. Profil-> startActivity(Intent(this, Bottom::class.java).apply { finish() })
                R.id. Add -> startActivity(Intent(this, ActivityAdd::class.java).apply { finish() })
            }
            finish()
            true
        }
    }
    private fun BtnOut(){
        binding.btnOut1.setOnClickListener {
            Constant.DeletToken(this)
            Constant.ClearNama(this)
            Constant.ClearUsername(this)
            startActivity(Intent(this,MainActivity::class.java))
            Toast.makeText(applicationContext, "Berhasil Log Out", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    private fun getUser(){
        binding.teksNama.text = "Hai "+Constant.GetNama(this)
        binding.teksUsername.text = "Username : "+Constant.GetUsername(this)
        binding.teksEmail.text = "Email : "+Constant.GetEmail(this)
    }
}