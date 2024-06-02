package com.leon.studentapp.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.squareup.picasso.Picasso
import java.net.URL

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String?) {
    if (url!=null){
        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(imageView)
        imageView.visibility = View.VISIBLE
    }
}
