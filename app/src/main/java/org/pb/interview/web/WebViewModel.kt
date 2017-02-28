package org.pb.interview.web

import android.databinding.ObservableField

class WebViewModel(url:String){
    val isLoading = ObservableField<Boolean>()
    val url = ObservableField<String>(url)

    fun onLoadingStateChanged(isLoad:Boolean){
        isLoading.set(isLoad)
    }
}
