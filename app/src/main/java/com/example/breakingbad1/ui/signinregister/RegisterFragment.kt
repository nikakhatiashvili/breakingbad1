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
import com.example.breakingbad1.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment:Fragment() {
    private var binding:FragmentRegisterBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val data = bundle.getParcelable<Parcelable>("bundleKey")
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding==null){
            binding= FragmentRegisterBinding.inflate(inflater,container,false)
            register()
            bind()
        }
        return binding?.root
    }

    private fun register() {
        binding?.registerBtn?.setOnClickListener{
            when{
                TextUtils.isEmpty(binding?.emailEditTxt?.text.toString().trim(){ it <= ' '}) -> {
                    Toast.makeText(activity,"please enter username",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding?.passwordEditTxt?.text.toString().trim(){ it <= ' '}) -> {
                    Toast.makeText(activity,"please enter username",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val userName:String = binding?.emailEditTxt?.text.toString().trim{ it <= ' '}
                    val password:String = binding?.passwordEditTxt?.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(userName,password)
                        .addOnCompleteListener( OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful){
                                val firebaseUser:FirebaseUser = task.result!!.user!!

                                Toast.makeText(activity,"you are registered succesfully", Toast.LENGTH_SHORT).show()

                                setFragmentResult("requestKey", bundleOf("bundleKey" to userName))
                                setFragmentResult("requestKeys", bundleOf("bundleKeys" to password))
                                findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
                            }else{
                                Toast.makeText(activity,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            }
        }
    }

    private fun bind() {
        binding?.backTextview?.setOnClickListener{

            findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }
    }
}