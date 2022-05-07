package me.kzaman.chat_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.ui.activities.AuthActivity
import me.kzaman.chat_app.ui.activities.DashboardActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is signed in
            val intent = Intent(applicationContext, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(applicationContext, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}