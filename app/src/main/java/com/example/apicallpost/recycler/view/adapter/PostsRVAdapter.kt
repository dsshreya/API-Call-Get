package com.example.apicallpost.recycler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallpost.R
import com.example.apicallpost.model.Posts
import kotlinx.android.synthetic.main.layout_recyclerview_posts.view.*

class PostsRVAdapter(private val dataList: List<Posts>, var onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<PostsRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsRVViewHolder {
        return PostsRVViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.layout_recyclerview_posts,
                    parent,
                    false
                ),
            onClickListener
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PostsRVViewHolder, position: Int) {

        holder.initialize(dataList[position])
        /*holder.title.text = dataList[position].title
        holder.body.text = dataList[position].body*/
    }
}

class PostsRVViewHolder(itemView: View, click: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
    /*var title = itemView.tvTitleDisplay
    var body = itemView.tvBody*/

    fun initialize(post: Posts) {
        itemView.apply {
            tvTitleDisplay.text = post.title
            tvBody.text = post.body
            tag = post
        }
    }

    init {
        itemView.setOnClickListener(click)
    }
}



