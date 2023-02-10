package com.example.basicloginsignup.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val email: String?,
    val password: String?
)