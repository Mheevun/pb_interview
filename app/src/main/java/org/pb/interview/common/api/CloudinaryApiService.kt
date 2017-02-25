package org.pb.interview.common.api

import com.cloudinary.Cloudinary
import io.reactivex.Observable
import javax.inject.Inject

/**
 * service for accessing cloudinaryApi
 */
class CloudinaryApiService @Inject constructor(val cloudinaryApi: CloudinaryApi, val cloudinary: Cloudinary) {
    val TAG:String? = CloudinaryApiService::class.simpleName
    fun getImageListResponse(tag:String): Observable<ImageListQueryResponse> {
        return cloudinaryApi.getImageList(tag)
    }

    fun toResourceObservable(response: ImageListQueryResponse):Observable<Resource>{
        return Observable.fromIterable(response.resources)
    }

    fun toURLObservable(publicId:String?):Observable<String>{
        return Observable.just(cloudinary.url().generate(publicId))
    }


    fun getImageURL(tag:String):Observable<String>{
        return cloudinaryApi.getImageList(tag)
                .flatMap { toResourceObservable(it) }
                .flatMap { toURLObservable(it.publicId) }
    }


}