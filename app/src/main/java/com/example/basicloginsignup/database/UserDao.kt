package com.example.basicloginsignup.database

import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface Dao {

    @Query("Select * From User")
    fun getAll() : List<User>

    @Insert
    fun addUser(user: User)

    @Query("Select * From User WHERE email LIKE :email AND" + "password LIKE :password")
    fun authUser(email : String, password : String) : User
}