package com.example.basicloginsignup.database

import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface UserDao {

    @Query("Select * From User")
    fun getAll() : List<User>

    @Insert
    fun addUser(user: User)

    @Query("Select * From User WHERE email LIKE :email")
    fun authUser(email : String) : Boolean

    @Query("Select * From User WHERE email LIKE :email AND "+"password LIKE :password")
    fun getCurrentUser(email : String, password : String) : User


}