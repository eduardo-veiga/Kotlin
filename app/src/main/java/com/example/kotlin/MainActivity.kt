package com.example.kotlin

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.Image
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
        var editText = findViewById<EditText>(R.id.textLogon)
        //editText.setText("usuario@usuario.com")
        var editSenha = findViewById<EditText>(R.id.textSenha)
        MeuLogin()
        NewCadastro()

        auth = Firebase.auth
        //createAccount(editText.text.toString(),editSenha.text.toString())

    }

    public fun MeuLogin() {
        var button = findViewById<Button>(R.id.buttonLogon)

        var editText = findViewById<EditText>(R.id.textLogon)
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

    public fun NewCadastro(){

        var buttoncad = findViewById<Button>(R.id.buttonCreate)

        var editText = findViewById<EditText>(R.id.textLogon)
        //editText.setText("usuario@usuario.com")
        var editSenha = findViewById<EditText>(R.id.textSenha)
        buttoncad.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Logado::class.java)

            auth.createUserWithEmailAndPassword(editText.text.toString(), editSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                        Toast.makeText(
                            baseContext, "Create success.",
                            Toast.LENGTH_SHORT

                        ).show()
                        intent.putExtra("user", editText.text.toString())
                        intent.putExtra("senha",editSenha.text.toString())
                        startActivity(intent).apply {  }
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

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }

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