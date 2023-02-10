package com.example.basicloginsignup.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.EditUserActivity
import com.example.basicloginsignup.R
import com.example.basicloginsignup.SharedPreference
import com.example.basicloginsignup.database.AppDatabase
import com.example.basicloginsignup.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var sharedPreference: SharedPreference? = null
    private var fragmentTransaction: FragmentTransaction? = null

    private var database: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        sharedPreference = context?.let { SharedPreference(it) }
        fragmentTransaction = parentFragmentManager.beginTransaction()

        database = context?.let { AppDatabase.getInstance(it) }

        binding.btnLogout.setOnClickListener {
            sharedPreference?.clearLogin()
            Toast.makeText(context, "sign-out", Toast.LENGTH_SHORT).show()
            fragmentTransaction?.replace(R.id.fragment_container, LoginFragment())?.commit()
        }

        binding.txtUsername.text = buildString {
            append("Hey ! ")
            append(sharedPreference?.getUser()?.email.toString())
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(activity, EditUserActivity::class.java)
            context?.startActivity(intent)
        }

        return binding.root
    }


}