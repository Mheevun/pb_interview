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

        if (state.isLoading){
            Log.d(TAG, "don't load image url: $url, state.isLoading: ${state.isLoading}, imageView.id: ${imageView.id}")
            return
        }

        Log.d(TAG, "load image url: $url")
        state.isLoading = true
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        state.isLoading = false
                    }

                    override fun onError() {
                        Log.w(TAG, "error load image url: $url")
                        state.isLoading = false
                    }

                })
    }

    fun createState(url: String):IsLoadingState{
        Log.d(TAG, "create state for url: $url")
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

}