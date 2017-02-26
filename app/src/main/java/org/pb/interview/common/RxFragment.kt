package org.pb.interview.common

import android.support.v4.app.Fragment
import android.util.Log
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.pb.interview.MainActivity
import org.pb.interview.common.di.scope.MainActivityScope
import javax.inject.Inject

/**
 * allow fragment object to create first in another thread
 * then add into the fragment stack later
 */

@MainActivityScope
class RxFragment(var factory: () -> Fragment, val tag: String) {
    private val TAG:String? = RxFragment::class.simpleName

    @Inject
    lateinit var fragmentHelper:FragmentHelper

    private var fragmentObsevable = createFragment()
    private var fragment:Fragment? = null
    init{
        MainActivity.mainActivityComponent.inject(this)
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
        //Maybe.just(true) is just dummy Maybe
        Maybe.zip(fragmentObsevable, Maybe.just(true),BiFunction<Fragment, Any, Any>{fragment, empty ->
            this.fragment = fragment
            gotoFragmentIfNotNull()
        }).subscribe()
    }

    private fun gotoFragmentIfNotNull(){
        fragmentHelper.gotoFragment(fragment!!, tag)
    }

}