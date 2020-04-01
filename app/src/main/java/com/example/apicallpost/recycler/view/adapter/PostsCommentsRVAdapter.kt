package com.example.apicallpost.recycler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallpost.R
import com.example.apicallpost.model.PostComments
import kotlinx.android.synthetic.main.layout_recyclerview_posts.view.*

class PostsCommentsRVAdapter(private val dataList: List<PostComments>) : RecyclerView.Adapter<PostsCommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsCommentsViewHolder {
        return PostsCommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.layout_recyclerview_posts,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PostsCommentsViewHolder, position: Int) {

        holder.initialize(dataList[position])
    }
}

class PostsCommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun initialize(postComments: PostComments){
        itemView.apply {
            tvTitleDisplay.textSize = 14F
            tvBody.textSize = 13F
            ivPostImageDisplay.layoutParams.height = 60
            ivPostImageDisplay.layoutParams.width = 60
            tvTitleDisplay.text =postComments.name
            tvBody.text = postComments.email
        }
    }
}
