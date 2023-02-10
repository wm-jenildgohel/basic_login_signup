package com.example.basicloginsignup.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "course_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            }
            return instance
        }
    }

    fun authUser(user: User): Boolean? {
        return user.email?.let { user.password?.let { it1 -> userDao()?.authUser(it, it1) } }
    }

    fun updateUser(user: User) {
        return userDao().updateUser(user)
    }

}