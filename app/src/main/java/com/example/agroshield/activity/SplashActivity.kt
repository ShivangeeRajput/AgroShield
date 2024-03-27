package com.example.agroshield.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.agroshield.R

class SplashActivity : AppCompatActivity() {
    lateinit var iv: ImageView
    val url="https://i.pinimg.com/originals/c1/26/9d/c1269d53fb511ec52ec52b4240690ab9.gif"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        iv=findViewById(R.id.iv)

        //using glide library
       Glide.with(this).load(url).into(iv)
        Handler().postDelayed({
            val intent=Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()},
            4000)
    }
}