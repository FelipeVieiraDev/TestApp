package com.example.testapp.ui.userList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.User
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class UserAdapter(val context: Context,
                  private val users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    fun addData(dbUsers: List<User>) {
        this.users.clear()
        this.users.addAll(dbUsers)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user = users[position]
        holder.itemView.nameTextView.text = user.firstName + " " + user.lastName

//        holder.itemView.setOnClickListener {
//            listener.onClick(users)
//        }
    }
}