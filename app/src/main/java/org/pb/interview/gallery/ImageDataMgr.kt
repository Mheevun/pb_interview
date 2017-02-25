package org.pb.interview.gallery

import android.util.Log
import io.reactivex.Observable
import org.pb.interview.common.api.CloudinaryApiService
import javax.inject.Inject

/**
 * provide the image from cloudinary
 */
class ImageDataMgr @Inject constructor(val apiService: CloudinaryApiService) {
    val TAG: String = ImageDataMgr::class.java.simpleName
    val CLOUDINARY_TAG = "test"

    fun getImageListURL(): Observable<String> {
        return apiService.getImageURL(CLOUDINARY_TAG)
                .doOnSubscribe { Log.d(TAG, "loading image url list") }
                .doAfterTerminate { Log.d(TAG, "finish load image url list") }
    }


    //        val requestURL = cloudinary.url().type("list").generate("$tag.json")
//        System.out.println(requestURL)
//        return mutableListOf()


}
