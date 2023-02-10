package com.example.basicloginsignup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.R
import com.example.basicloginsignup.SharedPreference
import com.example.basicloginsignup.Utils
import com.example.basicloginsignup.database.AppDatabase
import com.example.basicloginsignup.database.User
import com.example.basicloginsignup.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var fragmentTransaction: FragmentTransaction
    private var database: AppDatabase? = null
    private var sharedPreference: SharedPreference? = null

    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        fragmentTransaction = parentFragmentManager.beginTransaction()
        database = context?.let { AppDatabase.getInstance(it) }

        sharedPreference = context?.let { SharedPreference(it) }

        binding.btnLogin.setOnClickListener {
            val userEmail = binding.etLoginEmail.text.toString().trim()
            val userPassword = binding.etLoginPassword.text.toString().trim()

            if (!utils.emailValidations(userEmail)) {
                clearError()
                binding.tlLoginEmail.error = getString(R.string.invalid_email)
            } else if (!utils.passwordValidations(userPassword)) {
                clearError()
                binding.tlLoginPassword.error = getString(R.string.invalid_password)
            } else {
                clearError()
                val user = User(null, userEmail, userPassword)
                authUser(user)
            }
        }

        binding.tvGoToSignup.setOnClickListener {
            fragmentTransaction.replace(R.id.fragment_container, SignupFragment()).commit()
        }

        return binding.root
    }

    private fun clearError() {
        binding.tlLoginEmail.error = ""
        binding.tlLoginPassword.error = ""
    }

    private fun authUser(user: User) {
        if (database?.authUser(user) == true) {
            sharedPreference?.save(user)
            Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
            fragmentTransaction.replace(R.id.fragment_container, HomeFragment()).commit()
        } else {
            binding.etLoginEmail.error = getString(R.string.invalid_email)
        }
    }

}