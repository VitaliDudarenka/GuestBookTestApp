package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.addAnswer

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentAddAnswerBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter


class AddAnswerFragment : BaseMvvmFragment<AddAnswerViewModel, GuestBookRouter, FragmentAddAnswerBinding>() {

    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        private const val ID_USER_EXTRA = "ID_USER_EXTRA"
        fun getInstance(id: String, userId: String): AddAnswerFragment {
            val fragment = AddAnswerFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
            bundle.putString(ID_USER_EXTRA, userId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun provideViewModel(): AddAnswerViewModel {
        return ViewModelProviders.of(this).get(AddAnswerViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_add_answer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(AddAnswerFragment.ID_EXTRA)
        val userId = arguments?.getString(AddAnswerFragment.ID_USER_EXTRA)
        if (id != null) {
            viewModel.commentId = id
            viewModel.commentedUserId = id
        } else {
            router?.goBack()
        }

    }
}