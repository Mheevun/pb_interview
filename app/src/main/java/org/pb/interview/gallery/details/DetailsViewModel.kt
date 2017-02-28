package org.pb.interview.gallery.details

import android.databinding.ObservableField
import android.util.Log
import org.pb.interview.common.api.CloudinaryApiService
import javax.inject.Inject

class DetailsViewModel @Inject constructor(val cloudinaryApiService: CloudinaryApiService){
    val width = ObservableField<Int>()
    val height = ObservableField<Int>()
    val url = ObservableField<String>()
    val isLoading = ObservableField<Boolean>()

    init {

    }

    fun onLoadingStateChanged(isLoad:Boolean){
        Log.d("DetailsViewModel", "isLoad: $isLoad")
        isLoading.set(isLoad)
    }
}