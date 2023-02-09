package com.example.basicloginsignup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.R
import com.example.basicloginsignup.SharedPreference
import com.example.basicloginsignup.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var sharedPreference: SharedPreference? = null
    private var fragmentTransaction: FragmentTransaction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        sharedPreference = context?.let { SharedPreference(it) }
        fragmentTransaction = parentFragmentManager.beginTransaction()

        binding.btnLogout.setOnClickListener {
            sharedPreference?.clearLogin()
            fragmentTransaction?.replace(R.id.fragment_container, LoginFragment())
        }

        return binding.root
    }


}