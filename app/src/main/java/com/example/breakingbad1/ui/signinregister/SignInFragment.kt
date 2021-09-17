package com.example.breakingbad1.ui.signinregister

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.breakingbad1.R
import com.example.breakingbad1.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment: Fragment() {

    private var binding: FragmentSignInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null){
            binding = FragmentSignInBinding.inflate(inflater,container,false)
            login()
            bind()
            data()
        }
        return binding?.root
    }

    private fun data() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun login() {
        binding?.loginBtn?.setOnClickListener{
            when{
                TextUtils.isEmpty(binding?.usernameEditTxt?.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(activity,"please enter email.", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding?.passwordEditTxt?.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(activity,"please enter password.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email:String = binding?.usernameEditTxt?.text.toString().trim{ it <= ' '}
                    val password:String = binding?.passwordEditTxt?.text.toString().trim{ it <= ' '}
                    setFragmentResult("requestKeys", bundleOf("bundleKeys" to email))

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(activity,"you are logged in successfully.", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                        }else{
                            Toast.makeText(activity,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
    private fun bind() {
        binding?.registerTextview?.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }
    }
}