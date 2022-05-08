package me.kzaman.chat_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.chat_app.R
import me.kzaman.chat_app.base.BaseFragment
import me.kzaman.chat_app.databinding.FragmentRegisterBinding
import me.kzaman.chat_app.ui.activities.DashboardActivity
import me.kzaman.chat_app.utils.LoadingUtils
import me.kzaman.chat_app.utils.goToNextFragment


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var mAuth: FirebaseAuth

    var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    var userRef = database!!.getReference("users")

    override val layoutId: Int = R.layout.fragment_register

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

        binding.userLogin.setOnClickListener {
            goToNextFragment(R.id.action_registerFragment_to_loginFragment, binding.root, null)
        }

        binding.registerButton.setOnClickListener {
            if (binding.nameInputField.text.isNullOrEmpty()) {
                binding.nameInputField.error = "Required"
                return@setOnClickListener
            } else if (binding.emailInputField.text.isNullOrEmpty()) {
                binding.emailInputField.error = "Required"
                return@setOnClickListener
            } else if (binding.passwordInputField.text.isNullOrEmpty()) {
                binding.passwordInputField.error = "Required"
                return@setOnClickListener
            }

            loadingUtils.isLoading(true)
            mAuth.createUserWithEmailAndPassword(
                binding.emailInputField.text.toString().trim(),
                binding.passwordInputField.text.toString().trim()
            ).addOnCompleteListener(mActivity) {
                if (it.isSuccessful) {
                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap["name"] = binding.nameInputField.text.toString()
                    hashMap["email"] = binding.emailInputField.text.toString()
                    hashMap["image"] = "https://ui-avatars.com/api/?name=${binding.nameInputField.text}"
                    hashMap["status"] = "Hi there I'm using Chat App."

                    userRef.child(mAuth.currentUser?.uid!!).setValue(hashMap)
                        .addOnCompleteListener { user ->
                            if (user.isSuccessful) {
                                loadingUtils.isLoading(false)
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show()
                                val intent = Intent(mContext, DashboardActivity::class.java)
                                startActivity(intent)
                                mActivity.finish()
                            }
                        }

                } else {
                    loadingUtils.isLoading(false)
                    Toast.makeText(mContext, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}