package org.pb.interview.gallery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.pb.interview.R
import javax.inject.Inject

/**
 * grid view's item
 */
class ImageAdapter @Inject constructor(var context: Context, val imageDataMgr: ImageDataMgr, val imageLoader: ImageLoader) : BaseAdapter() {
    val TAG:String? = ImageAdapter::class.simpleName
    private val inflater: LayoutInflater
    private val items = mutableListOf<String>()
//    val cloudinary = Cloudinary(Utils.cloudinaryUrlFromContext(context))
    init {
        this.inflater = LayoutInflater.from(context)
        imageDataMgr.getImageListURL()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { addItem(it) }
                .subscribe()
    }

    fun addItem(url:String){
        items.add(url)
        notifyDataSetChanged()
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

    // Convert DP to PX
    // Source: http://stackoverflow.com/a/8490361
//    fun dpToPx(dps: Int): Int {
//        val scale = context.resources.displayMetrics.density
//        val pixels = (dps * scale + 0.5f).toInt()
//
//        return pixels
//    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(TAG, "position: $position")

        val itemLayout: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemLayout = inflater.inflate(R.layout.grid_item, parent, false)
            val imageView = itemLayout.findViewById(R.id.image_view) as ImageView
            viewHolder = ViewHolder(imageView, items[position])
            itemLayout.tag = viewHolder
        } else {
            itemLayout = convertView
            viewHolder = itemLayout.tag as ViewHolder
        }
        imageLoader.loadImage(viewHolder.url, viewHolder.imageView)
        return itemLayout
    }


    fun adjustImage(imageView:ImageView){

        // Set height and width constraints for the image view
//        imageView.layoutParams = LinearLayout.LayoutParams(wPixel, hPixel)

        // Set the content of the image based on the provided URI
//        imageView.setImageURI(
//                Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID)
//        )

        // Image should be cropped towards the center
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        // Set Padding for images
        imageView.setPadding(8, 8, 8, 8)

        // Crop the image to fit within its padding
        imageView.cropToPadding = true
    }



    class ViewHolder(var imageView: ImageView, var url:String)


}