package org.pb.interview.web

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import org.pb.interview.R
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentWebBinding



/**
 * View for showing website
 * according to URL input
 */
//TODO refactor to MVVM
class WebFragment(val url:String): Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = container!!.inflateBinding<FragmentWebBinding>(R.layout.fragment_web)
        binding.webview.setWebViewClient(object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean {
                view.loadUrl(url)
                return false
            }
        })
        binding.webview.loadUrl(url)


//        binding.webview.settings.useWideViewPort = true
//        binding.webview.settings.loadWithOverviewMode = true
//        binding.webview.setInitialScale(50)

        return binding.root
    }
}
