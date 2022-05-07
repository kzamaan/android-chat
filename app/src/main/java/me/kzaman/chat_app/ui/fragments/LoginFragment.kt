package me.kzaman.chat_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.ui.activities.DashboardActivity
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentLoginBinding
import me.kzaman.chat_app.utils.LoadingUtils
import me.kzaman.chat_app.utils.goToNextFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var mAuth: FirebaseAuth

    override val layoutId: Int = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        mActivity = requireActivity()
        loadingUtils = LoadingUtils(mContext)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        binding.lifecycleOwner = viewLifecycleOwner

        binding.createAccount.setOnClickListener {
            goToNextFragment(R.id.action_loginFragment_to_registerFragment, binding.root, null)
        }

        binding.loginButton.setOnClickListener {
            if(binding.emailInputField.text.isNullOrEmpty()){
                binding.emailInputField.error = "Required"
                return@setOnClickListener
            }
            else if(binding.passwordInputField.text.isNullOrEmpty()){
                binding.passwordInputField.error = "Required"
                return@setOnClickListener
            }
            loadingUtils.isLoading(true)
            mAuth.signInWithEmailAndPassword(
                binding.emailInputField.text.toString().trim(),
                binding.passwordInputField.text.toString().trim()
            ).addOnCompleteListener(mActivity) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(mContext, DashboardActivity::class.java)
                    startActivity(intent)
                    mActivity.finish()
                } else {
                    loadingUtils.isLoading(false)
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = it.exception?.message
                    // If sign in fails, display a message to the user.
                    Log.d("loginError", it.exception?.message.toString())
                }
            }
        }
    }
}