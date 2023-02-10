package com.example.basicloginsignup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.databinding.ActivityMainBinding
import com.example.basicloginsignup.fragments.LoginFragment
import com.example.basicloginsignup.fragments.HomeFragment
import com.example.basicloginsignup.database.User


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentTransaction
    private lateinit var sharedPreference: SharedPreference
    var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        sharedPreference = com.example.basicloginsignup.SharedPreference(applicationContext)
        user = sharedPreference.getUser()

        setUpFragments()
        setContentView(binding.root)

    }

    private fun setUpFragments() {
        fragmentManager = supportFragmentManager.beginTransaction()

        if (user?.email != null) {
            fragmentManager.replace(R.id.fragment_container, HomeFragment()).commit()
        } else {
            fragmentManager.replace(R.id.fragment_container, LoginFragment()).commit()
        }

    }


}