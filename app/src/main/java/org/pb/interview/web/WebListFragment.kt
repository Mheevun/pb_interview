package org.pb.interview.web

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.pb.interview.MainActivity
import org.pb.interview.R
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.FragmentWeblistBinding
import javax.inject.Inject

/**
 * View for showing URL list
 */
class WebListFragment : Fragment(), URLNavigator {
    @Inject
    lateinit var fragmentHelper: FragmentHelper
    init {
        initDI()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = container!!.inflateBinding<FragmentWeblistBinding>(R.layout.fragment_weblist)
        binding.viewModel = WebListViewModel(this)
        return binding.root
    }
    override fun goToURL(url: String) {
        fragmentHelper.gotoFragment(WebFragment(url), "Web Fragment")
    }

    fun initDI(){
        MainActivity.mainActivityComponent.inject(this)
    }
}