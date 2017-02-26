package org.pb.interview.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.pb.interview.App
import org.pb.interview.R
import org.pb.interview.common.api.CloudinaryApiService
import org.pb.interview.common.di.DaggerCloudinaryNetComponent
import org.pb.interview.common.di.NetModule
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentGalleryBinding
import org.pb.interview.gallery.pick.ImagePicker
import javax.inject.Inject

/**
 * Fragment for gallery management
 */

class GalleryFragment : Fragment() {
    val TAG: String? = GalleryFragment::class.simpleName
    @Inject
    lateinit var imageDataMgr: ImageDataMgr

    @Inject
    lateinit var apiService:CloudinaryApiService

    lateinit var binding: FragmentGalleryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDI()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = container!!.inflateBinding(R.layout.fragment_gallery)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        val adapter = ImageAdapter(context, imageDataMgr, ImageLoader(context))
        gridview.adapter = adapter
        binding.viewModel = GalleryViewModel(adapter, ImagePicker(this), apiService)//TODO move to dagger
    }

    fun initDI() {
        DaggerCloudinaryNetComponent.builder()
                .appComponent(App.appComponent)
                .netModule(NetModule("http://res.cloudinary.com/"))
                .build()
                .inject(this)
    }
}