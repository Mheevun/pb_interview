package org.pb.interview.common

import io.reactivex.Maybe


class RxInstance{
    val TAG:String? = RxInstance::class.simpleName

    companion object {
        inline fun <reified T> create(crossinline factory: () -> T): Maybe<T> {
            return Maybe.create<T> {
                it.onSuccess(factory())
            }
        }
    }
}