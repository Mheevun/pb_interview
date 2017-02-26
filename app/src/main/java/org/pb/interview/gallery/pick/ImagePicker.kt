package org.pb.interview.gallery.pick

import android.app.Activity
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import io.reactivex.Observable
import org.pb.interview.gallery.GalleryFragment


class ImagePicker(var targetUI:GalleryFragment){

    fun pickImage():Observable<String>{
        return RxPaparazzo.takeImage(targetUI)
                .usingCamera()
                .filter { response -> response.resultCode() == Activity.RESULT_OK }
                .flatMap { response -> Observable.just(response.data())  }
//                .subscribe{ response ->
//                    // See response.resultCode() doc
//                    if (response.resultCode() != Activity.RESULT_OK) {
//                        Log.d(TAG, "user cancel")
//                    }
//                    Log.d(TAG, "respons.data: "+response.data())
//                }
    }

}