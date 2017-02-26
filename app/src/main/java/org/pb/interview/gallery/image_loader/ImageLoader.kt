package org.pb.interview.gallery.image_loader

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.pb.interview.R

class ImageLoader(var context: Context) {
    val TAG: String? = ImageLoader::class.simpleName

    fun loadFitImage(url: String, imageView: ImageView) {
        Picasso.with(context).cancelRequest(imageView)
        Picasso.with(context)
                .load(url)
                .fit()
                .error(R.drawable.ic_crop_original_black_24dp)
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        Log.d(TAG, "load image success")
                    }
                    override fun onError() {
                        Log.w(TAG, "error load image url: $url")
                    }
                })
    }

    fun loadImage(url: String, imageView: ImageView){
        Picasso.with(context).cancelRequest(imageView)
        Picasso.with(context)
                .load(url)
                .error(R.drawable.ic_crop_original_black_24dp)
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        Log.d(TAG, "load image success")
                    }
                    override fun onError() {
                        Log.w(TAG, "error load image url: $url")
                    }
                })
    }


}