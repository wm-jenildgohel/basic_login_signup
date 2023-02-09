package com.example.basicloginsignup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.databinding.ActivityMainBinding
import com.example.basicloginsignup.fragments.LoginFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        fragmentManager = supportFragmentManager.beginTransaction()

        fragmentManager.replace(R.id.fragment_container, LoginFragment()).commit()

        setContentView(binding.root)


    }
}