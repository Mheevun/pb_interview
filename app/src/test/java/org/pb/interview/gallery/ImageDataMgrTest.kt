package org.pb.interview.gallery

import com.cloudinary.Cloudinary
import org.junit.Before
import org.junit.Test

/**
 * Created by cnr on 25/2/2560.
 */
class ImageDataMgrTest {

    lateinit var cloudinary:Cloudinary
    lateinit var target: ImageDataMgr

    @Before
    fun setup(){
        //TODO change to mock
        val config = mutableMapOf<String,String>()
        config.put("cloud_name", "ppwasin")
        config.put("api_key", "776679255158782")
        config.put("api_secret", "vXS7pgJ9Ge8fF6amdqMamOvmhjY")
        cloudinary = Cloudinary(config)

//        target = ImageDataMgr(cloudinary,"test")
    }

    @Test
    fun could_get_image_id_list(){
        System.out.println("test")
//        val idList:List<String> = target.getImageIds()
    }
}