package org.pb.interview.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import org.pb.interview.R
import org.pb.interview.gallery.image_loader.ImageLoader
import javax.inject.Inject

/**
 * provide grid's items
 */
class ImageAdapter @Inject constructor(var inflater: LayoutInflater, val imageLoader: ImageLoader) : BaseAdapter() {
        val TAG:String? = ImageAdapter::class.simpleName
    private val items = mutableListOf<String>()

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
            Log.d(TAG, "convertView is null")
            itemLayout = inflater.inflate(R.layout.grid_item, parent, false)
            val imageView = itemLayout.findViewById(R.id.image_view) as ImageView

            viewHolder = ViewHolder(imageView)
            itemLayout.tag = viewHolder
        } else {
            itemLayout = convertView
            viewHolder = itemLayout.tag as ViewHolder
        }
        Log.d(TAG, "########start loader image of $position, image is null: ${viewHolder.imageView} ##############")
        imageLoader.loadImage(items[position], viewHolder.imageView)
        addClickToDetail(viewHolder.imageView, items[position])
        Log.d(TAG, "########load image into imageView of position $position##############")

        return itemLayout
    }

    fun addClickToDetail(imageView:ImageView, url:String){
        imageView.setOnClickListener {

        }
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



    class ViewHolder(var imageView: ImageView){

    }

}