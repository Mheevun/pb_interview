package org.pb.interview.gallery

import android.databinding.ObservableField
import android.util.Log
import android.widget.BaseAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.pb.interview.common.api.CloudinaryApiService
import org.pb.interview.gallery.image_picker.ImagePicker


class GalleryViewModel(
        var adapter: ImageAdapter,
        var imagePicker: ImagePicker,
        val apiService: CloudinaryApiService) {
    val TAG: String? = GalleryViewModel::class.simpleName
    val adapterObservable = ObservableField<BaseAdapter>(adapter)
    val disposables = CompositeDisposable()

    init {
        disposables.add(
                apiService.getImageURL()
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { adapter.addItem(it) }
                        .doOnNext { adapter.notifyDataSetChanged() }
                        .subscribe())
    }

    fun pickImage() {
        Log.d(TAG, "on click")
        disposables.add(
                imagePicker.pickImage()
                        .observeOn(Schedulers.io())
                        .flatMapMaybe { path -> apiService.uploadImage(path) }
                        .map { response -> response["url"] as String }
                        .doOnNext { adapter.addItem(it) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { adapter.addItem(it) }
                        .subscribeOn(Schedulers.io())
                        .subscribe())
    }

    fun close(){
        disposables.dispose()
        disposables.clear()
    }
}