package com.example.testapp.ui.userRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testapp.MainActivity
import com.example.testapp.R
import com.example.testapp.data.User
import kotlinx.android.synthetic.main.fragment_user_register.*

class UserRegisterFragment : Fragment() {

    private lateinit var viewModel: UserRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[UserRegisterViewModel::class.java]

        return inflater.inflate(R.layout.fragment_user_register, container, false)
    }

    private fun updateView(viewState: UserRegisterViewModel.ViewState) {
        when (viewState) {
            is UserRegisterViewModel.ViewState.EmptyNames -> cleanNames()
            is UserRegisterViewModel.ViewState.SaveUser -> saveUser(viewState.user)
            is UserRegisterViewModel.ViewState.ShowErrorDialog -> showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alerta de erro")
        builder.setMessage("Os nomes não estão preenchidos corretamente")
        builder.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObserverViewState(viewModel)
        setListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setListeners() {
        saveButton.setOnClickListener {
            viewModel.saveUser(firstNameEditText.text,lastNameEditText.text)
        }
    }


    private fun setupObserverViewState(viewModel: UserRegisterViewModel) {
        viewModel.viewState.observe(viewLifecycleOwner, {
            updateView(it)
        })
    }

    private fun saveUser(User: User) {
        (activity as MainActivity).db.userDao().insertAll(User)
        findNavController().navigate(R.id.navigation_dashboard)
    }

    private fun cleanNames() {
        firstNameEditText.text.clear()
        lastNameEditText.text.clear()
    }
}