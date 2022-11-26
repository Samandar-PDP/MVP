package com.example.mvparch.presenter

import com.example.mvparch.model.Post
import com.example.mvparch.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl(private val mainView: MainView) : MainPresenter {
    override fun getAllPostList() {
        RetroInstance.retrofitInstance().getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    mainView.onPostListSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                mainView.onPostListFailure(t.message!!)
            }
        })
    }

    override fun deletePost(id: Int) {
        RetroInstance.retrofitInstance().deletePost(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    mainView.onPostDeleteSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message!!)
            }
        })
    }
}