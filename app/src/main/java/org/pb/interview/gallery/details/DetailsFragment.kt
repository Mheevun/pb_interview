package org.pb.interview.gallery.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.pb.interview.MainActivity
import org.pb.interview.R
import org.pb.interview.common.SystemUIHelper
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentImageDetailsBinding
import javax.inject.Inject



/**
 * manage image details
 */
class DetailsFragment: Fragment() {
    @Inject
    lateinit var viewModel:DetailsViewModel

    @Inject
    lateinit var systemUIHelper: SystemUIHelper


    lateinit var url:String
    val STATE_URL = "url"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.mainActivityComponent.inject(this)

        systemUIHelper.hideSystemUI()

        if(savedInstanceState!=null){
            url = savedInstanceState.getString(STATE_URL)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        systemUIHelper.showSystemUI()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = container?.inflateBinding<FragmentImageDetailsBinding>(R.layout.fragment_image_details)
        binding?.viewModel = viewModel
        viewModel.url.set(url)
        return binding?.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_URL, url)
        super.onSaveInstanceState(outState)
    }
}