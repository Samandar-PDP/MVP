package com.example.mvparch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvparch.R
import com.example.mvparch.adapter.PostAdapter
import com.example.mvparch.databinding.ActivityMainBinding
import com.example.mvparch.model.Post
import com.example.mvparch.presenter.MainPresenter
import com.example.mvparch.presenter.MainPresenterImpl
import com.example.mvparch.presenter.MainView

class MainActivity : AppCompatActivity(), MainView {
    private val postAdapter by lazy { PostAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var presenter: MainPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MainPresenterImpl(this)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
        presenter.getAllPostList()
        postAdapter.onDeleteClick = {
            AlertDialog.Builder(this).apply {
                setTitle("Delete")
                setMessage("Do you want to delete this post?")
                setPositiveButton("Yes") { di, _ ->
                    di.dismiss()
                    presenter.deletePost(it.id)
                }
                setNeutralButton("Cancel", null)
            }.create().show()
        }
    }

    override fun onPostListSuccess(posts: List<Post>) {
        postAdapter.submitList(posts)
        binding.progressBar.isVisible = false
    }

    override fun onPostListFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onPostDeleteSuccess(post: Post?) {
        Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onPostDeleteFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}