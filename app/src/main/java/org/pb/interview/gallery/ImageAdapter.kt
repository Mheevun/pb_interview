package org.pb.interview.gallery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.cloudinary.Cloudinary
import com.cloudinary.android.Utils
import com.squareup.picasso.Picasso
import org.pb.interview.R

/**
 * grid view's item
 */
class ImageAdapter(var context: Context) : BaseAdapter() {
    val TAG:String? = ImageAdapter::class.simpleName
    private val inflater: LayoutInflater
    val cloudinary = Cloudinary(Utils.cloudinaryUrlFromContext(context))
    var items = mutableListOf<String>()
    init {
        this.inflater = LayoutInflater.from(context)
        val url = cloudinary.url().generate("sample.jpg")
        items.add(url)
        items.add(url)
        items.add(url)
        items.add(url)
        items.add(url)
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
    fun dpToPx(dps: Int): Int {
        val scale = context.resources.displayMetrics.density
        val pixels = (dps * scale + 0.5f).toInt()

        return pixels
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item_layout: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            item_layout = inflater.inflate(R.layout.grid_item, parent, false)
            viewHolder = ViewHolder(item_layout, items[position])
            item_layout.tag = viewHolder
        } else {
            item_layout = convertView
            viewHolder = item_layout.tag as ViewHolder
        }
        loadImage(viewHolder)

        return item_layout
    }

    fun loadImage(viewHolder:ViewHolder){
        Log.d(TAG, "load image "+viewHolder.url)
        Picasso.with(context)
                .load(viewHolder.url)
                .fit()
                .placeholder(R.drawable.ic_crop_original_black_24dp)
                .into(viewHolder.imageView)
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



    class ViewHolder(val item_layout: View, var url:String){
        var imageView = item_layout.findViewById(R.id.image_view) as ImageView

    }


}