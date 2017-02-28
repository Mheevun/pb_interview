package org.pb.interview.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun <T: ViewDataBinding> ViewGroup.inflateBinding(layoutId: Int, attachToRoot: Boolean = false): T {
    return DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId,
            this,
            attachToRoot
    )
}