package com.example.breakingbad1.ui.profile

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.breakingbad1.databinding.FragmentHomeBinding
import com.example.breakingbad1.databinding.ProfileBinding

class ProfileFragment:Fragment() {
    private var binding:ProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFragmentResultListener("requestKeys"){ requestKey, bundle ->
            val userName = bundle.getString("bundleKeys")
            binding?.usernameTextviewForProfile?.text = userName.toString()
        }
        if(binding == null){
            binding = ProfileBinding.inflate(inflater,container,false)

        }
        return binding?.root
    }




}