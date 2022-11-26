package com.example.mvparch.presenter

import com.example.mvparch.model.Post

interface MainView {
    fun onPostListSuccess(posts: List<Post>)
    fun onPostListFailure(error: String)

    fun onPostDeleteSuccess(post: Post?)
    fun onPostDeleteFailure(error: String)
}
