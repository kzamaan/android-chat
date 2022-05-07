package me.kzaman.chat_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var userName: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        userName = findViewById(R.id.userNameInputField)
        password = findViewById(R.id.passwordInputField)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                userName.text.toString().trim(),
                password.text.toString().trim()
            ).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("loginError", it.exception?.message.toString())
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is signed in
            val intent = Intent(applicationContext, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}