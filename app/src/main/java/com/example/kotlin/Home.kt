package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var buttonlogin = findViewById<Button>(R.id.btnlogin)
        var buttonsigin = findViewById<Button>(R.id.btnsign)

        buttonlogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        buttonsigin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        })
    }
}