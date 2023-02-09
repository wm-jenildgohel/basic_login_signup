package com.example.basicloginsignup.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java, "course_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            }
            return instance
        }
    }

    fun addUser(user: User) {
        userDao().addUser(user)
    }

    fun authUser(user: User): Boolean? {
        return user.email?.let { userDao().authUser(it) }
    }

}