package me.kzaman.chat_app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.adapter.TabLayoutAdapter
import me.kzaman.chat_app.databinding.ActivityDashboardBinding

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutAdapter = TabLayoutAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = tabLayoutAdapter
        val tabs: TabLayout = binding.tabLayout
        tabs.setupWithViewPager(viewPager)
    }
}