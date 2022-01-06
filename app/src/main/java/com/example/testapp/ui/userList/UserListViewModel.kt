package com.example.testapp.ui.userList

import androidx.lifecycle.*
import com.example.testapp.data.User
import com.example.testapp.data.UserRepository

class UserListViewModel (private val repository: UserRepository) : ViewModel() {

    val allUser: LiveData<List<User>> = repository.allUsers.asLiveData()

}

class UserListViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}