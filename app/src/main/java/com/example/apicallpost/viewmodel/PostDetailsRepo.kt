package com.example.apicallpost.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.apicallpost.api.client.PostApiClient
import com.example.apicallpost.api.client.RetrofitClient
import com.example.apicallpost.model.PostComments
import com.example.apicallpost.model.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsRepo {

    var postDetailsList: MutableLiveData<List<Posts>> = MutableLiveData()
    var postCommentsDetailsList : MutableLiveData<List<PostComments>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPostDetails() {
        isLoading.postValue(true)
        val service: PostApiClient? =
            RetrofitClient.getRetrofitInstance()
                ?.create(PostApiClient::class.java)
        service?.getPosts()?.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.let {
                    postDetailsList.postValue(it)
                    isLoading.postValue(false)
                }
            }

            override fun onFailure(call: Call<List<Posts>>, throwable: Throwable) {
                isLoading.postValue(false)

            }
        })
    }

    fun getPostCommentsDetails(id:Int?) {
        isLoading.postValue(true)
        val service: PostApiClient? =
            RetrofitClient.getRetrofitInstance()
                ?.create(PostApiClient::class.java)
        if (id != null) {
            service?.getPostComments(id)?.enqueue(object : Callback<List<PostComments>> {
                override fun onFailure(call: Call<List<PostComments>>, t: Throwable) {
                    isLoading.postValue(false)
                }

                override fun onResponse(call: Call<List<PostComments>>, response: Response<List<PostComments>>) {
                    response.body()?.let {
                        postCommentsDetailsList.postValue(it)
                        isLoading.postValue(false)
                    }
                }
            })
        }
    }
}