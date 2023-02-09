package com.example.basicloginsignup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.R
import com.example.basicloginsignup.SharedPreference
import com.example.basicloginsignup.Utils
import com.example.basicloginsignup.database.AppDatabase
import com.example.basicloginsignup.database.User
import com.example.basicloginsignup.database.UserDao
import com.example.basicloginsignup.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var fragmentTransaction: FragmentTransaction

    private var database: AppDatabase? = null
    private var userDao: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        fragmentTransaction = parentFragmentManager.beginTransaction()

        val sharedPreferences = context?.let { SharedPreference(it) }
        val utils = Utils()


        binding.tvGoToLogin.setOnClickListener {
            fragmentTransaction.replace(R.id.fragment_container, LoginFragment()).commit()
        }

        database = context?.let { AppDatabase.getInstance(it) }
        userDao = database?.userDao()
        binding.btnSignup.setOnClickListener {
            val userEmail = binding.etSignupEmail.text.toString().trim()
            val userPassword = binding.etSignupPassword.text.toString().trim()
            val userRe_Password = binding.etSignupRePassword.text.toString().trim()
            val user = User(null, userEmail, userPassword)

            if (!utils.emailValidations(userEmail)) {
                clearError()
                binding.tlSignupEmail.error = getString(R.string.invalid_email)
            } else if (!utils.passwordValidations(userPassword)) {
                clearError()
                binding.tlSignupPassword.error = getString(R.string.invalid_password)
            } else if (!utils.retyepePasswordValidations(userPassword, userRe_Password)) {
                clearError()
                binding.tlSignupRePassword.error = getString(R.string.password_not_matched)
            } else {
                clearError()
                if (authUser(user) != true) {
                    createUser(user)
                    sharedPreferences?.save(user)
                    Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT)
                        .show()
                    fragmentTransaction.replace(R.id.fragment_container, HomeFragment()).commit()
                } else {
                    binding.etSignupEmail.error = getString(R.string.user_exists)
                }
            }
        }

        return binding.root
    }

    private fun clearError() {
        binding.tlSignupEmail.error = ""
        binding.tlSignupPassword.error = ""
        binding.tlSignupRePassword.error = ""
    }

    private fun createUser(user: User) {
        userDao?.addUser(user)
    }

    private fun authUser(user: User): Boolean? {
        return user.email?.let { userDao?.authUser(it) }
    }
}
