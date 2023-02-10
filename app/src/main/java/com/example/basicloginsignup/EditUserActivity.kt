package com.example.basicloginsignup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.basicloginsignup.database.AppDatabase
import com.example.basicloginsignup.database.User
import com.example.basicloginsignup.databinding.ActivityEditUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserBinding
    private var sharedPreference: SharedPreference? = null
    private lateinit var fragmentTransaction: FragmentTransaction

    private var database: AppDatabase? = null
    private var utils: Utils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditUserBinding.inflate(layoutInflater)
        sharedPreference = SharedPreference(applicationContext)
        fragmentTransaction = supportFragmentManager.beginTransaction()

        database = applicationContext.let { AppDatabase.getInstance(it) }
        utils  = Utils()

        val userEmail: String = sharedPreference?.getUser()?.email.toString().trim()
        val userPassword: String = sharedPreference?.getUser()?.password.toString().trim()

        val user = database?.userDao()?.getCurrentUser(userEmail,userPassword)

        binding.etEditEmail.setText(user?.email)
        binding.etEditPassword.setText(user?.password)

        binding.btnEdit.setOnClickListener {
            val email = binding.etEditEmail.text.toString().trim()
            val password = binding.etEditPassword.text.toString().trim()

            if(utils?.emailValidations(email) == true && utils?.passwordValidations(password) == true){
                val updatedUser = User(user?.id,email,password)
                try{
                    lifecycleScope.launch(Dispatchers.IO) {
                        database?.userDao()?.updateUser(updatedUser)
                    }
                    sharedPreference?.save(updatedUser)
                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                catch (e : java.lang.Exception){
                    Toast.makeText(this, "Error occurred $e", Toast.LENGTH_SHORT).show()
                }

            }
        }

        setContentView(binding.root)
    }
}