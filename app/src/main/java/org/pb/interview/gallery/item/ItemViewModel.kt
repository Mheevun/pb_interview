package org.pb.interview.gallery.item

import android.util.Log
import android.view.View
import org.pb.interview.gallery.ImageAdapter

/**
 * ViewModel for gallery's item
 */
class ItemViewModel{
    val TAG:String? = ItemViewModel::class.simpleName
    fun onClickImage(view: View){
        val url = (view.tag as ImageAdapter.ViewHolder)
        Log.d(TAG, "url click: $url")
    }
}
