package org.pb.interview.common.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CloudinaryApi {

    @GET("ppwasin/image/list/{tag}.json")
    fun getImageList(@Path("tag") tag:String): Observable<ImageListQueryResponse>

}