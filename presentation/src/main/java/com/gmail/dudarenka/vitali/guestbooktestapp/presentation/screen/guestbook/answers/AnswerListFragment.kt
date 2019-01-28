package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.answers

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentAnswerListBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter


class AnswerListFragment : BaseMvvmFragment<AnswerListViewModel, GuestBookRouter, FragmentAnswerListBinding>() {
    private var layoutManager: LinearLayoutManager? = null

    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        private const val ID_USER_EXTRA = "ID_USER_EXTRA"
        fun getInstance(id: String, userId: String): AnswerListFragment {
            val fragment = AnswerListFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
            bundle.putString(ID_USER_EXTRA, userId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun provideViewModel(): AnswerListViewModel {
        return ViewModelProviders.of(this).get(AnswerListViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_answer_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRecyclerView.adapter = viewModel.adapter
        layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.setHasFixedSize(true)
        val id = arguments?.getString(ID_EXTRA)
        val userId = arguments?.getString(ID_USER_EXTRA)
        if (id != null) {
            viewModel.setCommentId(id, userId!!)
        } else {
            router?.goBack()
        }

    }

}