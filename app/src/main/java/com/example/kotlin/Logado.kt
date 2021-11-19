package com.example.kotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Logado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logado)
        val buttonSair = findViewById<Button>(R.id.buttonDeslogar)
        val textUser = findViewById<TextView>(R.id.textView_usuer)
        val textSenha = findViewById<TextView>(R.id.textView_senha)
        //testar comit delete

        val textSenha2 = intent.getStringExtra("senha" )
        textSenha.setText(intent.getStringExtra("senha"))
        //val toast = Toast.makeText(applicationContext,"senha"+ textSenha2,Toast.LENGTH_SHORT)
        //toast.show()
        Log.d("SENHA1",textSenha.toString())
        val textUser2 = intent.getStringExtra("user")
        textUser.setText(textUser2)


        buttonSair.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}