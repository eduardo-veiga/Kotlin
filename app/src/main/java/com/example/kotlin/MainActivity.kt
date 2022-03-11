package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var editText = findViewById<EditText>(R.id.text_email)
        //editText.setText("usuario@usuario.com")
        var editSenha = findViewById<EditText>(R.id.textSenha)
        MeuLogin()
        sendPasswordReset()

        auth = Firebase.auth
        //createAccount(editText.text.toString(),editSenha.text.toString())

    }

    public fun MeuLogin() {
        var button = findViewById<Button>(R.id.buttonLogon)

        var editText = findViewById<EditText>(R.id.text_email)
        //editText.setText("usuario@usuario.com")
        var editSenha = findViewById<EditText>(R.id.textSenha)

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Logado::class.java)
            //signIn(editText.text.toString(),editSenha.text.toString())

            auth.signInWithEmailAndPassword(editText.text.toString(), editSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(
                            baseContext, "Authentication success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = auth.currentUser
                        updateUI(user)
                        intent.putExtra("user", editText.text.toString())
                        intent.putExtra("senha",editSenha.text.toString())
                        startActivity(intent).apply {  }
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }

            //val toast = Toast.makeText(applicationContext,editSenha.text, Toast.LENGTH_SHORT)
            //toast.show()
        })

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun sendPasswordReset() {

        var btn_forgot = findViewById<Button>(R.id.buttonForgot)
        var emailAddress = findViewById<EditText>(R.id.text_email)
        // [START send_email_verification]
        btn_forgot.setOnClickListener(View.OnClickListener {
            Firebase.auth.sendPasswordResetEmail(emailAddress.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Toast.makeText(
                        baseContext, "Email send",
                        Toast.LENGTH_SHORT
                    ).show()
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


}


//sistemlog original
/*private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }*/