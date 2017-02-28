package org.pb.interview.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.pb.interview.MainActivity
import org.pb.interview.R
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.RxInstance
import org.pb.interview.common.api.CloudinaryApiService
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentGalleryBinding
import org.pb.interview.gallery.image_picker.ImagePicker
import javax.inject.Inject

/**
 * Fragment for gallery management
 */

class GalleryFragment : Fragment() {
    val TAG: String? = GalleryFragment::class.simpleName

    @Inject
    lateinit var apiService:CloudinaryApiService

    @Inject
    lateinit var fragmentHelper:FragmentHelper

    lateinit var binding: FragmentGalleryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        MainActivity.mainActivityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = container!!.inflateBinding(R.layout.fragment_gallery)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        createGalleryViewModel()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{viewModel -> binding.viewModel  = viewModel}

    }
    fun createGalleryViewModel():Maybe<GalleryViewModel>{
        return Maybe.zip(createAdapter(), createImagePicker(), BiFunction<ImageAdapter, ImagePicker, GalleryViewModel>{
            adapter, picker -> GalleryViewModel(adapter, picker, apiService)
        })
    }

    fun createAdapter(): Maybe<ImageAdapter>{
        return RxInstance.create { ImageAdapter(fragmentHelper) }
    }
    fun createImagePicker():Maybe<ImagePicker>{
        return RxInstance.create { ImagePicker(this) }
    }

}