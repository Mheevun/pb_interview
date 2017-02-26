package org.pb.interview.common.api

import android.util.Log
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * service for accessing cloudinaryApi
 */
class CloudinaryApiService @Inject constructor(val cloudinaryApi: CloudinaryApi, val cloudinary: Cloudinary) {
    val TAG:String? = CloudinaryApiService::class.simpleName
    val CLOUDINARY_TAG = "test"
    fun getImageListResponse(tag:String): Observable<ImageListQueryResponse> {
        return cloudinaryApi.getImageList(tag)
    }

    fun toResourceObservable(response: ImageListQueryResponse):Observable<Resource>{
        //reverse for showing the old one first
        return Observable.fromIterable(response.resources?.reversed())
    }

    fun toURLObservable(publicId:String?):Observable<String>{
        return Observable.just(cloudinary.url().generate(publicId))
    }


    fun getImageURL(tag:String = CLOUDINARY_TAG):Observable<String>{
        return cloudinaryApi.getImageList(tag)
                .observeOn(Schedulers.computation()) //do serialize process (below) on computation thread
                .concatMap { toResourceObservable(it) }
                .concatMap { toURLObservable(it.publicId) }
                .doOnSubscribe { Log.d(TAG, "loading image url list") }
                .doAfterTerminate { Log.d(TAG, "finish load image url list") }
    }


    fun uploadImage(imagePath:String, tag:String = CLOUDINARY_TAG): Maybe<MutableMap<Any?, Any?>> {
        return Maybe.create { emitter ->
            val response = cloudinary.uploader().upload(imagePath, ObjectUtils.asMap("tags", tag))
            Log.d(TAG, "upload -> response url: ${response["url"]}")
            emitter.onSuccess(response)
        }

    }

}