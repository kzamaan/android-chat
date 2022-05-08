package me.kzaman.chat_app.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.kzaman.chat_app.R
import me.kzaman.chat_app.ui.fragments.ChatsFragment
import me.kzaman.chat_app.ui.fragments.RequestFragment

private val TAB_TITLES = arrayOf(
    "Requests",
    "Chats",
    "Friends"
)

class TabLayoutAdapter(
    private val context: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RequestFragment()
            }
            1 -> {
                ChatsFragment()
            }
            else -> {
                RequestFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence = TAB_TITLES[position]
    override fun getCount(): Int = TAB_TITLES.size
}