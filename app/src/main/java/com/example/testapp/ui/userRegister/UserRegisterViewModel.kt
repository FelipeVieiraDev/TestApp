package com.example.testapp.ui.userRegister

import android.text.Editable
import androidx.lifecycle.*
import com.example.testapp.data.User
import com.example.testapp.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRegisterViewModel(private val repository: UserRepository) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class ShowNames: ViewState()
        class EmptyNames : ViewState()
        class SaveUser (val user: User) : ViewState()
        class ShowErrorDialog : ViewState()
    }

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        withContext(Dispatchers.IO){ repository.insert(user) }
    }

    fun saveUser(firstName: Editable, lastName: Editable) {
        if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty()){
            viewState.value = ViewState.ShowErrorDialog()
        } else {
            val user = User(firstName = firstName.toString(), lastName = lastName.toString(), uid = 0)
            viewState.value = ViewState.SaveUser(user)
        }
        viewState.value = ViewState.EmptyNames()
    }
}

class UserRegisterViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserRegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserRegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}