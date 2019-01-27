package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


@BindingAdapter("visibility")
fun View.visibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}




