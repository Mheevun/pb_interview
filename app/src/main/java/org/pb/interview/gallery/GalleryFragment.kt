package org.pb.interview.gallery

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.pb.interview.App
import org.pb.interview.R
import org.pb.interview.common.di.DaggerCloudinaryNetComponent
import org.pb.interview.common.di.NetModule
import javax.inject.Inject

/**
 * Fragment for gallery management
 */

class GalleryFragment:Fragment(){

    @Inject
    lateinit var imageDataMgr: ImageDataMgr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDI()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_gallery, container, false)
        return view
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        gridview.adapter = ImageAdapter(context, imageDataMgr, ImageLoader(context))
        RxPaparazzo.takeImage(this)
                .usingCamera()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{ response ->
                    // See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        Log.d(TAG, "user cancel")
                    }

                    response.targetUI().loadImage(response.data());
                }
    }

    fun initDI(){
        DaggerCloudinaryNetComponent.builder()
                .appComponent(App.appComponent)
                .netModule(NetModule("http://res.cloudinary.com/"))
                .build()
                .inject(this)
    }
}