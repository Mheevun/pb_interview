package org.pb.interview.common.di

import com.cloudinary.Cloudinary
import dagger.Module
import dagger.Provides
import org.pb.interview.common.api.CloudinaryApi
import retrofit2.Retrofit


@Module
class CloudinaryApiModule{
    @CloudinaryScope
    @Provides
    fun provideCloudinaryApi(retrofit: Retrofit): CloudinaryApi {
        return retrofit.create(CloudinaryApi::class.java)
    }

    @CloudinaryScope
    @Provides
    fun provideCloudinary(): Cloudinary {
        val config = mutableMapOf<String,String>()
        config.put("cloud_name", "ppwasin")
        config.put("api_key", "776679255158782")
        config.put("api_secret", "vXS7pgJ9Ge8fF6amdqMamOvmhjY")
        return Cloudinary(config)
    }
}
