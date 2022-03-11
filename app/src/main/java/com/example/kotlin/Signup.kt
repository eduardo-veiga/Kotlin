package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.AccessController.getContext

class Signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        NewCadastro()


        auth = Firebase.auth
    }

    public fun NewCadastro() {

        var btn_sginup = findViewById<Button>(R.id.btnSignup)

        var editText = findViewById<EditText>(R.id.text_email)
        //editText.setText("usuario@usuario.com")
        var editSenha = findViewById<EditText>(R.id.textSenha)
        var editSenha2 = findViewById<EditText>(R.id.textSenha2)


        btn_sginup.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            if (TextUtils.isEmpty(editText.getText())) {
                Toast.makeText(this, "Please input your Email", Toast.LENGTH_LONG).show();
                editText.setError("Please input your Email");
            } else {

                if (TextUtils.isEmpty(editSenha.text)) {
                    Toast.makeText(this, "Please input your Password", Toast.LENGTH_LONG).show();
                    editSenha.setError("Please input your Password");
                } else {
                    if (TextUtils.isEmpty(editSenha2.text)) {
                        Toast.makeText(this, "Please confirm your Password", Toast.LENGTH_LONG)
                            .show();
                        editSenha2.setError("Please confirm your Password");
                    } else {
                        if ( editSenha.text.toString() != editSenha2.text.toString()) {
                            Toast.makeText(this, "Password dosen't match", Toast.LENGTH_LONG)
                                .show();
                            editSenha2.setError("Password dosen't match");
                        } else {
                            val intent = Intent(this,Logado::class.java)

                            auth.createUserWithEmailAndPassword(editText.text.toString(), editSenha.text.toString())
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(MainActivity.TAG, "createUserWithEmail:success")
                                        val user = auth.currentUser
                                        updateUI(user)
                                        Toast.makeText(
                                            baseContext, "Create success.",
                                            Toast.LENGTH_SHORT

                                        ).show()
                                        intent.putExtra("user", editText.text.toString())
                                        intent.putExtra("senha",editSenha.text.toString())
                                        startActivity(intent).apply {  }
                                        finish()

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(MainActivity.TAG, "createUserWithEmail:failure", task.exception)
                                        Toast.makeText(
                                            baseContext, "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        updateUI(null)
                                    }
                                }

                        }

                    }
                }

            }


        })


    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

}