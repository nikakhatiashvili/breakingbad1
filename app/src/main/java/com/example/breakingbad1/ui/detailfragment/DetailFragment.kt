package com.example.breakingbad1.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.example.breakingbad1.databinding.DetailFragmentBinding
import com.example.breakingbad1.model.Characters

class DetailFragment: Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var binding: DetailFragmentBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val data = bundle.getParcelable<Characters>("bundleKey")
            binding?.apply {
                birthday.text = data?.birthday
                status.text = data?.status
                Protrayed.text = data?.portrayed
                Glide.with(requireContext()).load(data?.img).into(binding!!.characterImg)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null){
            binding = DetailFragmentBinding.inflate(inflater,container,false)
        }
        return binding?.root
    }
}