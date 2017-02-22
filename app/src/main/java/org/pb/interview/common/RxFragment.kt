package org.pb.interview.common

import android.support.v4.app.Fragment
import android.util.Log
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * allow fragment object to create first in another thread
 * then add into the fragment stack later
 */

class RxFragment(val fragmentHelper:FragmentHelper, var factory: () -> Fragment, val tag: String) {
    private val TAG:String? = RxFragment::class.simpleName
    private var fragmentObsevable = createFragment()
    private var fragment:Fragment? = null
    init{
        createFragmentInTheBackground()
    }

    private fun createFragmentInTheBackground(){
        Log.d(TAG, "create")
        fragmentObsevable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
    private fun createFragment(): Maybe<Fragment> {
        return Maybe.create<Fragment> {
            it.onSuccess(factory())
        }
    }

    fun gotoFragment() {
        if(fragment==null){
            waitForFragmentToCreateThenAddTheFragment()
        }else{
            gotoFragmentIfNotNull()
        }
    }

    private fun waitForFragmentToCreateThenAddTheFragment(){
        Maybe.zip(fragmentObsevable, Maybe.just(true),BiFunction<Fragment, Boolean, Any>{fragment, empty ->
            this.fragment = fragment
            gotoFragmentIfNotNull()
        }).subscribe()
    }

    private fun gotoFragmentIfNotNull(){
        fragmentHelper.gotoFragment(fragment!!, tag)
    }

}