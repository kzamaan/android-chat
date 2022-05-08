package me.kzaman.chat_app.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import me.kzaman.chat_app.base.BaseActivity
import me.kzaman.chat_app.databinding.ActivitySettingsBinding
import me.kzaman.chat_app.utils.LoadingUtils

class SettingsActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var userRef = database!!.getReference("users")

    override fun initializeApp() {}
    override fun setToolbarTitle(title: String) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingUtils = LoadingUtils(this)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        loadingUtils.isLoading(true)
        userRef.child(currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.userProfile.text = snapshot.toString()
                loadingUtils.isLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("error", error.toString())
                loadingUtils.isLoading(false)
            }
        })

    }
}