package org.pb.interview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * Helper for navigate around fragment
 */
class FragmentNavigator(var supportFragmentManager: FragmentManager){
    fun addFragment(fragment: Fragment, tag:String){
        supportFragmentManager.beginTransaction().add(fragment, tag).addToBackStack(tag).commit()
    }

}
