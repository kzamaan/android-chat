package me.kzaman.chat_app.ui.fragments

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentRegisterBinding
import me.kzaman.chat_app.utils.LoadingUtils
import me.kzaman.chat_app.utils.goToNextFragment

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private lateinit var binding: FragmentRegisterBinding
    override val layoutId: Int = R.layout.fragment_register

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

        binding.userLogin.setOnClickListener {
            goToNextFragment(R.id.action_registerFragment_to_loginFragment, binding.root, null)
        }
    }
}