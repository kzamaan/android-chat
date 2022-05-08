package me.kzaman.chat_app.ui.fragments

import android.os.Bundle
import android.view.View
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentChatsBinding
import me.kzaman.chat_app.utils.LoadingUtils

class ChatsFragment : BaseFragment<FragmentChatsBinding>() {
    private lateinit var binding: FragmentChatsBinding

    override val layoutId: Int = R.layout.fragment_chats

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