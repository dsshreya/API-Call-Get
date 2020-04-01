package com.example.apicallpost.api.client

import com.example.apicallpost.model.PostComments
import com.example.apicallpost.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiClient {

    //https://jsonplaceholder.typicode.com/  posts
    //Specify the request type and pass the relative URL//
    @GET ("/posts")
    //Wrap the response in a Call object with the type of the expected result//
    fun getPosts() : Call<List<Posts>>

    @GET("/posts/{id}/comments")
    fun getPostComments(
        @Path("id") id: Int
    ) : Call<List<PostComments>>

   /*@GET("{id}")
    fun groupList(
        @Path("id") id: Int,
        @Query("format") format: String?
    ): Call<List<User?>?>?*/
}