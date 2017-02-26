package org.pb.interview.common.api

import com.cloudinary.Cloudinary
import com.cnr.phr_android.ImmediateSchedulerRule
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CloudinaryApiServiceTest {
    val mockResponse = ImageListQueryResponse()
    val tag: String = "tag"
    val publicIdList = mutableListOf("1", "2", "3", "4")
    lateinit var urlList:MutableList<String>
    lateinit var target: CloudinaryApiService

    @Mock
    lateinit var cloudinaryApi: CloudinaryApi

    @Rule @JvmField
    var immediateSchedulerRule = ImmediateSchedulerRule()

    var cloudinary = createCloudinary()

    @Before
    fun setup() {
        mockResponse.resources = createImages()
        urlList = createURLList(publicIdList)
        whenever(cloudinaryApi.getImageList(tag)).thenReturn(Observable.just(mockResponse))

        target = CloudinaryApiService(cloudinaryApi, cloudinary)
    }
    private fun createURLList(publicIdList:MutableList<String>):MutableList<String>{
        val list = mutableListOf<String>()
        publicIdList.mapTo(list) { createURL(it) }
        return  list
    }
    private fun createURL(publicId:String):String{
        return cloudinary.url().generate(publicId)
    }
    private fun createCloudinary():Cloudinary{
        val config = mutableMapOf<String,String>()
        config.put("cloud_name", "ppwasin")
        config.put("api_key", "776679255158782")
        config.put("api_secret", "vXS7pgJ9Ge8fF6amdqMamOvmhjY")
        return Cloudinary(config)
    }
    private fun createImages(): List<Resource> {
        val list = mutableListOf<Resource>()
        publicIdList.forEach { list.add(createImage(it)) }
        return list
    }
    private fun createImage(publicId: String): Resource {
        val resource = Resource()
        resource.publicId = publicId
        return resource
    }

    @Test
    fun should_get_pojo_response() {
        target.getImageListResponse(tag).test()
                .assertValueCount(1)
                .assertValue { compare(it.resources!!, mockResponse.resources!!) }
    }

    @Test
    fun should_get_list_of_image_url_in_any_order() {
        target.getImageURL(tag).test()
                .assertValueCount(urlList.size)
                .assertValueSet(urlList)
    }

    @Test
    fun should_get_list_of_image_url_in_inverse_order() {
        target.getImageURL(tag).test()
                .assertValueSequence(urlList.reversed())
    }

    private fun compare(resources: List<Resource>, expectResource: List<Resource>): Boolean {
        for (i in 0..resources.size - 1) {
            if (resources[i].publicId != expectResource[i].publicId) {
                return false
            }
        }
        return true
    }

}