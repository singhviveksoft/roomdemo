package com.example.userdemo.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imgUrl")
fun ImageView.imgUrl(str:String) {
    Picasso.get().load(str).into(this)
}