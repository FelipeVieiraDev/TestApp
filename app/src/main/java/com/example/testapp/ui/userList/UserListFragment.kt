package com.example.testapp.ui.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.MainActivity
import com.example.testapp.MyApplication
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment() {

//    private lateinit var viewModel: UserListViewModel
    private lateinit var adapter : UserAdapter

    private val userListViewModel: UserListViewModel by viewModels {
        UserListViewModelFactory((activity?.application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapters()
        fillAdapters()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fillAdapters() {
        userListViewModel.alluser.observe(viewLifecycleOwner, { users ->
            users?.let { adapter.addData(it) }
        })
    }

    private fun setAdapters() {
        adapter = UserAdapter(requireActivity().applicationContext, ArrayList())
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}