package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework4.Models.PostModel
import com.example.homework4.databinding.ActivityAddPostBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class AddPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPostBinding
    private val posts = FirebaseDatabase.getInstance().reference.child("posts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addPost()
    }

    fun addPost() {
        binding.addButton.setOnClickListener {
            val description = binding.DescriptionEdiText.text.toString();
            val imageUrl = binding.PostImageEditText.text.toString();
            if (description.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show()
            } else {
                val post = PostModel(description, imageUrl)
                val id = UUID.randomUUID().toString();
                posts.child(id).setValue(post).addOnSuccessListener {
                    finish()
                };
            }
        }
    }
}