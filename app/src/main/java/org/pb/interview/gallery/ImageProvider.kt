package org.pb.interview.gallery

import com.cloudinary.Cloudinary
import io.reactivex.Flowable

/**
 * Created by cnr on 25/2/2560.
 */
class ImageProvider(val cloudinary: Cloudinary, val tag:String){
    val TAG:String = ImageProvider::class.java.simpleName
    fun listImageView(): Flowable<String>{
        return Flowable.empty()

    }

    fun getImageIds():List<String>{
        val requestURL = cloudinary.url().type("list").generate("$tag.json")
        System.out.println(requestURL)
        return mutableListOf()
    }
}
