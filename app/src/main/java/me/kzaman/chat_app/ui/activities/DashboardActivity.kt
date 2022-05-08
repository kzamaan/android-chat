package me.kzaman.chat_app.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.R
import me.kzaman.chat_app.adapter.TabLayoutAdapter
import me.kzaman.chat_app.base.BaseActivity
import me.kzaman.chat_app.databinding.ActivityDashboardBinding

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun initializeApp() {}
    override fun setToolbarTitle(title: String) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutAdapter = TabLayoutAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = tabLayoutAdapter
        val tabs: TabLayout = binding.tabLayout
        tabs.setupWithViewPager(viewPager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
                true
            }
            R.id.account_setting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}