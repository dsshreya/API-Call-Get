package com.example.apicallpost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class PostViewModel(application: Application)  : AndroidViewModel(application){

    var postDetailsRepo = PostDetailsRepo()
    var postDetailsList = postDetailsRepo.postDetailsList
    var postCommentsDetailsList  = postDetailsRepo.postCommentsDetailsList

    var isLoading = postDetailsRepo.isLoading

    fun getPostDetails(){
        postDetailsRepo.getPostDetails()
    }

    fun getPostCommentsDetails(id: Int?) {
        postDetailsRepo.getPostCommentsDetails(id)
    }

}