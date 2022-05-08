package me.kzaman.chat_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentChatsBinding
import me.kzaman.chat_app.databinding.FragmentFriendsBinding
import me.kzaman.chat_app.utils.LoadingUtils

class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {
    private lateinit var binding: FragmentFriendsBinding

    override val layoutId: Int = R.layout.fragment_friends

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        mActivity = requireActivity()
        loadingUtils = LoadingUtils(mContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        binding.lifecycleOwner = viewLifecycleOwner
    }
}