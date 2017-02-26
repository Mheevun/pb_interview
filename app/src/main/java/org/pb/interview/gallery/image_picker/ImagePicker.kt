package org.pb.interview.gallery.image_picker

import android.app.Activity
import android.support.v4.app.Fragment
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import io.reactivex.Observable

class ImagePicker(var targetUI:Fragment){

    fun pickImage():Observable<String>{
        return RxPaparazzo.takeImage(targetUI)
                .usingCamera()
                .filter { response -> response.resultCode() == Activity.RESULT_OK }
                .flatMap { response -> Observable.just(response.data())  }
    }

}