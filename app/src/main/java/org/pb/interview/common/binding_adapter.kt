package org.pb.interview.common

import android.databinding.BindingAdapter
import android.widget.BaseAdapter
import android.widget.GridView

@BindingAdapter("adapter")
fun setAdapter(gridView:GridView, adapter:BaseAdapter?){
    gridView.adapter = adapter
}