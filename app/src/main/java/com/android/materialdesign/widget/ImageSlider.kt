package com.android.materialdesign.widget

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.materialdesign.R

class ImageAdapter(var context: Context):PagerAdapter(){

    var slideImageId:IntArray = intArrayOf(R.drawable.lion,R.drawable.horse)


    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setImageResource(slideImageId[position])
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

    override fun getCount(): Int {
        return slideImageId.size
    }

}