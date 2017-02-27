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
    val isLoading = ObservableField<Boolean>()
    val isUploading = ObservableField<Boolean>()

    init {
        apiService.getImageURL()
                .doOnSubscribe { isLoading.set(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { adapter.addItem(it) }
                .doOnComplete { adapter.notifyDataSetChanged() }
                .doOnComplete { isLoading.set(false) }
                .subscribe()

    }

    fun pickImage() {
        Log.d(TAG, "on click")
        imagePicker.pickImage()
                .subscribeOn(Schedulers.io())
                .flatMapMaybe { path -> apiService.uploadImage(path) }
                .doOnSubscribe { isUploading.set(true) }
                .map { response -> response["url"] as String }
                .doOnNext { adapter.addItem(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { adapter.notifyDataSetChanged() }
                .subscribe()

    }

}