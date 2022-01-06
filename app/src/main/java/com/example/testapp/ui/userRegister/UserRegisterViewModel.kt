package com.example.testapp.ui.userRegister

import android.text.Editable
import androidx.lifecycle.*
import com.example.testapp.data.User
import com.example.testapp.data.UserRepository
import kotlinx.coroutines.launch

class UserRegisterViewModel(private val repository: UserRepository) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class ShowNames: ViewState()
        class EmptyNames : ViewState()
        class SaveUser (val user: User) : ViewState()
        class ShowErrorDialog : ViewState()
    }

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val alluser: LiveData<List<User>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun saveUser(firstName: Editable, lastName: Editable) {
        if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty()){
            viewState.value = ViewState.ShowErrorDialog()
        } else {

            val user = User(firstName = firstName.toString(), lastName = lastName.toString(), uid = 0)

            viewState.value = ViewState.SaveUser(user)
        }
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