package com.example.apicallpost.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicallpost.Constants
import com.example.apicallpost.R
import com.example.apicallpost.model.PostComments
import com.example.apicallpost.model.Posts
import com.example.apicallpost.recycler.view.adapter.PostsCommentsRVAdapter
import com.example.apicallpost.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_display.*

class DisplayFragment : Fragment() {
    var postViewModel: PostViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post =  arguments?.getParcelable<Posts>(Constants.POSTS_ID)
//            Log.d("TAG", "id : $post")
        if (post != null) {
            addData(post)
        }
           postViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
               .create(PostViewModel::class.java)
           postViewModel?.getPostCommentsDetails(post?.id)
        observeStates()

    }


    private fun observeStates() {
        postViewModel?.isLoading?.observe(this, Observer {
            it?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })

        postViewModel?.postCommentsDetailsList?.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    loadDataList(it)
                }
            }
        })
    }

    private fun addData(data: Posts) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_recyclerview_posts, rvContainerPostDisplay, false)

        view?.findViewById<TextView>(R.id.tvTitleDisplay)?.text = data.title
        view?.findViewById<TextView>(R.id.tvBody)?.text = data.body

        rvContainerPostDisplay.addView(view)

    }

    private fun loadDataList(usersList: List<PostComments>) {
        rvContainerPostComments.layoutManager = LinearLayoutManager(context)
        rvContainerPostComments.adapter = PostsCommentsRVAdapter(usersList)
    }
}
