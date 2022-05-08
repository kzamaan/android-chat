package me.kzaman.chat_app.ui.fragments

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentRequestBinding
import me.kzaman.chat_app.utils.LoadingUtils

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding>() {
    private lateinit var binding: FragmentRequestBinding

    override val layoutId: Int = R.layout.fragment_request

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