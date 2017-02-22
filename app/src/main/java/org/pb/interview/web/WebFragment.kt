package org.pb.interview.web

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.pb.interview.R
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentWebBinding

/**
 * View for showing website
 * according to URL input
 */

class WebFragment(val url:String): Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = container!!.inflateBinding<FragmentWebBinding>(R.layout.fragment_web)
        binding.webview.loadUrl(url)
        return binding.root
    }
}
