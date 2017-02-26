package org.pb.interview.common

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import org.pb.interview.common.di.scope.MainActivityScope


/**
 * Helper for navigate around fragment
 */
@MainActivityScope
class FragmentHelper(var supportFragmentManager: FragmentManager, @LayoutRes var layoutId: Int){
    fun gotoFragment(fragment: Fragment, tag:String, addToBackStack:Boolean = true){
        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(layoutId, fragment)
        if (addToBackStack){
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }

}
