package org.pb.interview.gallery

import android.databinding.ObservableField
import android.util.Log
import android.widget.BaseAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.pb.interview.common.api.CloudinaryApiService
import org.pb.interview.gallery.image_picker.ImagePicker


class GalleryViewModel(
        var adapter: ImageAdapter,
        var imagePicker: ImagePicker,
        val apiService: CloudinaryApiService) {
    val TAG: String? = GalleryViewModel::class.simpleName
    val adapterObservable = ObservableField<BaseAdapter>(adapter)

    init {
        apiService.getImageURL()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { adapter.addItem(it) }
                .doOnNext { adapter.notifyDataSetChanged() }
                .subscribe()

    }

    fun pickImage() {
        Log.d(TAG, "on click")
        imagePicker.pickImage()
                .flatMapMaybe { path -> apiService.uploadImage(path) }
                .map { response -> response["url"] as String }
                .doOnNext { adapter.addItem(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { adapter.notifyDataSetChanged() }
                .subscribeOn(Schedulers.io())
                .subscribe()

    }

}