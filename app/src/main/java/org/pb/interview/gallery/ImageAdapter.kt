package org.pb.interview.gallery

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.pb.interview.R
import org.pb.interview.common.FragmentHelper
import org.pb.interview.common.RxInstance
import org.pb.interview.common.inflateBinding
import org.pb.interview.databinding.GridItemBinding
import org.pb.interview.gallery.details.DetailsFragment
import org.pb.interview.gallery.image_loader.ImageLoader

/**
 * provide grid's items
 */
class ImageAdapter (val fragmentHelper: FragmentHelper) : BaseAdapter() {
    val TAG:String? = ImageAdapter::class.simpleName
    private val items = mutableListOf<String>()
    private val imageLoader = ImageLoader()

    fun addItem(url:String){
        Log.d(TAG, "addItem: $url at ${items.size}")
        items.add(items.size, url)
    }


    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any? {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(TAG, "position: $position")

        val itemLayout: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            Log.d(TAG, "convertView is null")
            val binding = parent.inflateBinding<GridItemBinding>(R.layout.grid_item)
            itemLayout = binding.root
            viewHolder = ViewHolder(binding)
            itemLayout.tag = viewHolder
        } else {
            itemLayout = convertView
            viewHolder = itemLayout.tag as ViewHolder
        }
        val imageView =  viewHolder.binding.imageView
        imageLoader.loadImageWithRecycleView(items[position], imageView)
        addClickToDetail( imageView, items[position])

        return itemLayout
    }

    fun addClickToDetail(imageView:ImageView, url:String){
        imageView.setOnClickListener {
            RxInstance.create { DetailsFragment() }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        it.url = url
                        fragmentHelper.gotoFragment(it, "Detail")
                    }
        }
    }


    class ViewHolder(var binding: GridItemBinding)

}