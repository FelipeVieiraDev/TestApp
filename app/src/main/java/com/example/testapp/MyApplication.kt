package com.example.testapp

import android.app.Application
import com.example.testapp.data.AppDatabase
import com.example.testapp.data.UserRepository

class MyApplication: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}