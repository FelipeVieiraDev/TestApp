package com.example.testapp

import android.app.Application
import com.example.testapp.data.AppDatabase
import com.example.testapp.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}