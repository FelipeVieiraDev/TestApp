package com.example.testapp.ui.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.MainActivity
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var adapter : UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]

        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapters()
        fillAdapters()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fillAdapters() {
        (activity as MainActivity).db.userDao().getAll().let {
            adapter.addData(it)
        }
    }

    private fun setAdapters() {
        adapter = UserAdapter(requireActivity().applicationContext, ArrayList())
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}