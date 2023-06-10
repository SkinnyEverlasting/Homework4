package com.example.homework4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework4.Adapters.PostAdapter
import com.example.homework4.Models.PostModel
import com.example.homework4.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val posts = FirebaseDatabase.getInstance().reference.child("posts")
    private lateinit var binding: ActivityMainBinding
    private val postAdapter : PostAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.postLayout.adapter = postAdapter
        setListener();
        addPost();
    }


    private fun setListener() {
        posts.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postModels = snapshot.children.map {
                    it.getValue(PostModel::class.java)!!
                }
                postAdapter.setData(postModels)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun addPost() {
        binding.addButton.setOnClickListener{
            var intent = Intent(this, AddPostActivity::class.java);
            startActivity(intent)
        }
    }
}