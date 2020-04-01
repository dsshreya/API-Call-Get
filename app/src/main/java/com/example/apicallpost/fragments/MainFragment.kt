package com.example.apicallpost.fragments

import android.app.Application
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicallpost.Constants
import com.example.apicallpost.R
import com.example.apicallpost.model.Posts
import com.example.apicallpost.recycler.view.adapter.PostsRVAdapter
import com.example.apicallpost.viewmodel.PostViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {
    var postViewModel: PostViewModel? = null
    var snackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            .create(PostViewModel::class.java)
        postViewModel?.getPostDetails()
        observeStates()
    }

    private fun observeStates() {
        postViewModel?.isLoading?.observe(this, Observer {
            it?.let {
                if (it) {
                    progressBarMF.visibility = View.VISIBLE
                } else {
                    progressBarMF.visibility = View.GONE
                }
            }
        })
        postViewModel?.postDetailsList?.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    loadDataList(it)
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rvContainerPosts -> {
                val connectivityManager =
                    context?.getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetwork
                if (networkInfo != null) {
                    val fragment = DisplayFragment()
                    val bundle = Bundle()
                    val postDetail = v.tag as Posts
//                bundle.putInt("id",postDetail.id)
                    bundle.putParcelable(Constants.POSTS_ID, postDetail)
                    fragment.arguments = bundle
                    fragmentManager?.beginTransaction()?.replace(R.id.mainActivityFrame, fragment)
                        ?.addToBackStack(null)?.commit()
                }
            }
        }
    }

    private fun loadDataList(usersList: List<Posts>) {
        rvPosts.layoutManager = LinearLayoutManager(context)
        rvPosts.adapter =
            PostsRVAdapter(
                usersList,
                this
            )
    }
}

