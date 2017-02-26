package org.pb.interview.common

import android.databinding.BindingAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import org.pb.interview.gallery.image_loader.ImageLoader

@BindingAdapter("adapter")
fun setAdapter(gridView:GridView, adapter:BaseAdapter?){
    gridView.adapter = adapter
}

@BindingAdapter("url")
fun setAdapter(imageView: ImageView, url:String){
    ImageLoader(imageView.context).loadImage(url, imageView)
}