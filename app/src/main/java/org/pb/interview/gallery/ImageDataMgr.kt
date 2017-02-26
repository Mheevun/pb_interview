package org.pb.interview.gallery

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
    }

    fun uploadImage(imagePath:String){

    }

    //        val requestURL = cloudinary.url().type("list").generate("$tag.json")
//        System.out.println(requestURL)
//        return mutableListOf()


}
