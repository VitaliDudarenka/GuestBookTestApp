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
        fun getInstance(id: String): AddAnswerFragment {
            val fragment = AddAnswerFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
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
        if (id != null) {
            viewModel.commentId = id
        } else {
            router?.goBack()
        }

    }
}