package org.pb.interview.gallery.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
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
        Picasso.with(context)
                .load(url)
                .error(R.drawable.ic_crop_original_black_24dp)
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(object:Target{
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) { }
                    override fun onBitmapFailed(errorDrawable: Drawable?) {  }
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        val width = bitmap?.width ?: 0
                        val height = bitmap?.height ?: 0
                        Log.d(TAG, "onBitmapLoaded width x height : $width x $height")
                        imageView.layoutParams =  FrameLayout.LayoutParams(width, height)
                        imageView.setImageBitmap(bitmap)
                    }
                })
    }


}