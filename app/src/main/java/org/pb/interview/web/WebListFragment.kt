package org.pb.interview.web

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.pb.interview.R
import org.pb.interview.common.FragmentNavigator
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentWeblistBinding

/**
 * View for showing URL list
 */
class WebListFragment(val fragmentNavigator: FragmentNavigator) : Fragment(), URLNavigator {
    val TAG:String? = WebListFragment::class.simpleName
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "container is null? $container")
        val binding = container!!.inflateBinding<FragmentWeblistBinding>(R.layout.fragment_weblist)
        binding.viewModel = WebListViewModel(this)
        return binding.root
    }
    override fun goToURL(url: String) {
        fragmentNavigator.addFragment(WebFragment(url), "Web Fragment")
    }
}