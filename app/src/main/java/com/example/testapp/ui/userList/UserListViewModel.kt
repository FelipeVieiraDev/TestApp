package com.example.testapp.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel : ViewModel() {

    val viewState = MutableLiveData<ViewState>()


    sealed class ViewState {
        class ShowNames: ViewState()
        class EmptyNames : ViewState()
    }
}