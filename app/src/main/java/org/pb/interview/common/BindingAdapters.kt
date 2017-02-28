package org.pb.interview.common

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import com.wang.avi.AVLoadingIndicatorView
import org.pb.interview.gallery.image_loader.ImageLoader

@BindingAdapter("url", "onLoadingStateChanged")
fun setWebURL(webview:WebView, url:String, onLoadingStateChanged: OnLoadingStateChanged){
    webview.setWebViewClient(object: WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean {
            view.loadUrl(url)
            return false
        }
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            onLoadingStateChanged.isLoaded(true)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            onLoadingStateChanged.isLoaded(false)
        }
    })
    webview.loadUrl(url)
}

@BindingAdapter("adapter")
fun setAdapter(gridView:GridView, adapter:BaseAdapter?){
    gridView.adapter = adapter
}

@BindingAdapter("url")
fun setImageURL(imageView: ImageView, url:String){
    ImageLoader().loadImage(url, imageView)
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
    ImageLoader().loadImage(url, imageView, onLoadingStateChanged)
}

interface OnLoadingStateChanged {
    fun isLoaded(isLoad:Boolean)
}
