package com.example.apicallpost.model

data class PostComments (
    var postId : Int,
    var id : Int,
    var name  : String,
    var email : String,
    var body : String
)