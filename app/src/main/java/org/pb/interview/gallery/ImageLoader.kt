package org.pb.interview.gallery

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.pb.interview.R

class ImageLoader(var context: Context) {
    val TAG: String? = ImageLoader::class.simpleName
    val stateList = mutableListOf<IsLoadingState>()

    fun loadImage(url: String, imageView: ImageView) {
        val state = getStateOrCreateIfNotExist(url)

        if (state.isAlreadyLoad || state.isLoading)
            return

        Log.d(TAG, "load image url: $url")
        state.isLoading = true
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        state.isLoading = true
                        state.isAlreadyLoad = true
                    }

                    override fun onError() {
                        Log.w(TAG, "error load image url: $url")
                        state.isLoading = true
                    }

                })
    }

    fun createState(url: String):IsLoadingState{
        val state = IsLoadingState(url)
        stateList.add(state)
        return state
    }

    fun getStateOrCreateIfNotExist(url: String): IsLoadingState {
        return getStateByURL(url) ?: createState(url)
    }

    fun getStateByURL(url: String): IsLoadingState? {
        return stateList.filter { it.url == url }.singleOrNull()
    }

    class IsLoadingState(var url: String, var isLoading: Boolean = false, var isAlreadyLoad: Boolean = false)
}