package org.pb.interview.common

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * Helper for navigate around fragment
 */
class FragmentNavigator(var supportFragmentManager: FragmentManager, @LayoutRes var layoutId: Int){
    fun addFragment(fragment: Fragment, tag:String){
        supportFragmentManager.beginTransaction().replace(layoutId, fragment).addToBackStack(tag).commit()
    }

}
