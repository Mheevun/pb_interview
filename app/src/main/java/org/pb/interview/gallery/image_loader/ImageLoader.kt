package org.pb.interview.gallery.image_loader

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import org.pb.interview.R
import org.pb.interview.common.OnLoadingStateChanged

class ImageLoader {
    val TAG: String? = ImageLoader::class.simpleName

    companion object {
        //prevent jvm to garbage collect target during image loading
        var target: Target? = null
    }

    fun loadImageWithRecycleView(url: String, imageView: ImageView, listener: OnLoadingStateChanged? = null) {
        val context = imageView.context

        Picasso.with(context).cancelRequest(imageView)
        listener?.isLoaded(true)
        Picasso.with(context)
                .load(url)
                .fit()
                .error(R.drawable.ic_crop_original_black_24dp)
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(imageView, getCallback(listener))
    }

    private fun getCallback(listener: OnLoadingStateChanged? = null):Callback{
        return object : Callback {
            override fun onSuccess() {
                listener?.isLoaded(false)
            }
            override fun onError() {
                listener?.isLoaded(false)
            }
        }
    }


    fun loadImage(url: String, imageView: ImageView, listener: OnLoadingStateChanged? = null) {
        target = getPicassoTarget(imageView, listener)

        Log.d(TAG, "url: $url")
        Picasso.with(imageView.context)
                .load(url)
                .error(R.drawable.ic_crop_original_black_24dp)
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(target)
    }

    private fun getPicassoTarget(imageView: ImageView, listener: OnLoadingStateChanged?): Target {
        return object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                Log.d(TAG, "prepare to load")
                listener?.isLoaded(true)
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {
                Log.d(TAG, "load image fail")
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                Log.d(TAG, "onBitmapLoaded")
                val width = bitmap?.width ?: 0
                val height = bitmap?.height ?: 0
                Log.d(TAG, "onBitmapLoaded width x height : $width x $height")
                imageView.layoutParams = FrameLayout.LayoutParams(width, height)
                imageView.setImageBitmap(bitmap)
                listener?.isLoaded(false)
                target = null
            }
        }
    }

}
