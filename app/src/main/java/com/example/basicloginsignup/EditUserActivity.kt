package com.example.basicloginsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.basicloginsignup.database.AppDatabase
import com.example.basicloginsignup.databinding.ActivityEditUserBinding
import com.example.basicloginsignup.databinding.FragmentHomeBinding

class EditUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserBinding
    private var sharedPreference: SharedPreference? = null
    private var fragmentTransaction: FragmentTransaction? = null

    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditUserBinding.inflate(layoutInflater)
        sharedPreference = applicationContext.let { SharedPreference(it) }
        fragmentTransaction = supportFragmentManager.beginTransaction()

        database = applicationContext.let { AppDatabase.getInstance(it) }


        binding.btnEdit.setOnClickListener {
            val email = binding.etEditEmail.text.toString().trim()
            val password = binding.etEditPassword.text.toString().trim()
        }

        setContentView(binding.root)
    }
}