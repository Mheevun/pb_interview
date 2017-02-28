package org.pb.interview.common

import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import com.wang.avi.AVLoadingIndicatorView
import org.pb.interview.gallery.image_loader.ImageLoader

@BindingAdapter("adapter")
fun setAdapter(gridView:GridView, adapter:BaseAdapter?){
    gridView.adapter = adapter
}

@BindingAdapter("url")
fun setURL(imageView: ImageView, url:String){
    ImageLoader(imageView.context).loadImage(url, imageView)
}

@BindingAdapter("play")
fun playLoadingIndicator(view: AVLoadingIndicatorView, play:Boolean?){
    Log.d("binding_adapter","play: $play")
    if(play==null||play==false) {
        view.visibility = View.INVISIBLE
        view.hide()
    }
    else {
        view.visibility = View.VISIBLE
        view.show()
    }
}

@BindingAdapter("url", "onLoadingStateChanged")
fun setURLWithListener(imageView: ImageView, url: String, onLoadingStateChanged: OnLoadingStateChanged){
    ImageLoader(imageView.context).loadImage(url, imageView, onLoadingStateChanged)
}

interface OnLoadingStateChanged {
    fun isLoaded(isLoad:Boolean)
}
