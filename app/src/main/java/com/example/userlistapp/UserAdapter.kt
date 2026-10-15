package com.example.userlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.tvName)
        val email: TextView = view.findViewById(R.id.tvEmail)
        val phone: TextView = view.findViewById(R.id.tvPhone)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = users[position]

        holder.name.text = user.name
        holder.email.text = user.email
        holder.phone.text = user.phone

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "You selected ${user.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = users.size
}