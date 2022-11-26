package com.example.mvparch.presenter

import com.example.mvparch.model.Post


interface MainPresenter {
    fun getAllPostList()
    fun deletePost(id: Int)
}