package com.example.basicloginsignup.database

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface UserDao {

    /* @Query("Select * From User")
     suspend fun getAll() : List<User>*/

    @Insert
    fun addUser(user: User)

    @Query("Select * From User WHERE email LIKE :email AND " + "password LIKE :password")
    fun getCurrentUser(email: String, password: String): User

    @Query("Select * From User WHERE email LIKE :email AND " + "password LIKE :password")
    fun authUser(email: String, password: String): Boolean

    @Update
    fun updateUser(user: User)
}