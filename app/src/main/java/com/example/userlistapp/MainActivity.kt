package com.example.userlistapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnFetchUsers: Button
    private lateinit var progressBar: ProgressBar

    private val userList = mutableListOf<User>()
    private lateinit var userAdapter: UserAdapter

    private val url = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnFetchUsers = findViewById(R.id.btnFetchUsers)
        progressBar = findViewById(R.id.progressBar)

        userAdapter = UserAdapter(userList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter

        btnFetchUsers.setOnClickListener {
            fetchUsers()
        }
    }

    private fun fetchUsers() {

        progressBar.visibility = View.VISIBLE
        btnFetchUsers.isEnabled = false

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                progressBar.visibility = View.GONE
                btnFetchUsers.isEnabled = true

                try {
                    val fetchedUsers = mutableListOf<User>()

                    for (i in 0 until response.length()) {
                        val userObject = response.getJSONObject(i)

                        val name = userObject.getString("name")
                        val email = userObject.getString("email")
                        val phone = userObject.getString("phone")

                        fetchedUsers.add(User(name, email, phone))
                    }

                    userList.clear()
                    userList.addAll(fetchedUsers)
                    userAdapter.notifyDataSetChanged()

                    Toast.makeText(
                        this,
                        "Users fetched successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Failed to parse user data.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            { error ->

                progressBar.visibility = View.GONE
                btnFetchUsers.isEnabled = true

                Toast.makeText(
                    this,
                    "Failed to fetch data. Check your connection.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }
}