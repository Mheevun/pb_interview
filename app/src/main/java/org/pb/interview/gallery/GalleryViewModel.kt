package org.pb.interview.gallery

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.pb.interview.common.api.CloudinaryApiService
import org.pb.interview.gallery.pick.ImagePicker


class GalleryViewModel(
        var adapter:ImageAdapter,
        var imagePicker:ImagePicker,
        val apiService: CloudinaryApiService) {
    val TAG:String? = GalleryViewModel::class.simpleName

    fun pickImage(){
        Log.d(TAG, "on click")
        imagePicker.pickImage()
                .flatMapMaybe { path -> apiService.uploadImage(path) }
                .map { response -> response["url"] as String }
                .doOnNext { adapter.addItem(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({adapter.notifyDataSetChanged()})
    }
}