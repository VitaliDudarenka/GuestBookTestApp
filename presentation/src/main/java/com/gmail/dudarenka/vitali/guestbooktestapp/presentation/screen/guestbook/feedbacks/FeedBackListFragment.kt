package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.feedbacks

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentFeedbackListBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter


class FeedBackListFragment : BaseMvvmFragment<FeedBackListViewModel, GuestBookRouter, FragmentFeedbackListBinding>() {
    private var layoutManager: LinearLayoutManager? = null

    companion object {
        fun getInstance(): FeedBackListFragment {
            return FeedBackListFragment()
        }
    }

    override fun provideViewModel(): FeedBackListViewModel {
        return ViewModelProviders.of(this).get(FeedBackListViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_feedback_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRecyclerView.adapter = viewModel.adapter
        layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.setHasFixedSize(true)
        binding.listRecyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager!!) {
            override fun onLoadMore() {
                viewModel.loadItems()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.setToken()
    }
}