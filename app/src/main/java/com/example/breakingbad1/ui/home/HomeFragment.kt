package com.example.breakingbad1.ui.home

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingbad1.R
import com.example.breakingbad1.databinding.FragmentHomeBinding
import com.example.breakingbad1.model.Characters
import com.example.breakingbad1.network.callbackinterface.ClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter:HomeAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null){
            binding = FragmentHomeBinding.inflate(inflater,container,false)
            viewModel.load()
            bind()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView: BottomNavigationView = binding!!.navView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.profileFragment
            )
        )
    }

    private fun bind() {

        adapter = HomeAdapter(object : ClickListener {
            override fun onClick(data: Characters) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to data))

                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            }
        })


        binding?.recyclerviewFORCharacters?.adapter = adapter
        binding?.recyclerviewFORCharacters?.layoutManager = GridLayoutManager(requireContext(),2)
        viewModel.characters.observe(viewLifecycleOwner){
            adapter.data = it
        }
    }
}