package com.podium.technicalchallenge.presentation.binding

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text?.let {
            Toast.makeText(view.context, it, Toast.LENGTH_LONG).show()
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.bindLoadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun ImageView.bindLoadCircleImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
