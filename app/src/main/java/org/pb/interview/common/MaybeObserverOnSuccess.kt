package org.pb.interview.common

import android.support.v4.app.Fragment
import io.reactivex.observers.DisposableMaybeObserver

class MaybeObserverOnSuccess(val onSuccessFunc: (Fragment) -> Unit) : DisposableMaybeObserver<Fragment>() {

    override fun onError(e: Throwable?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(fragment: Fragment) {
        onSuccessFunc(fragment)
    }

    override fun onComplete() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}